package com.example.nfctagwriter

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.*
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var editTextData: EditText
    private lateinit var textViewStatus: TextView

    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    private var writingTagFilters: Array<IntentFilter>? = null

    private val TAG = "NfcWriter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextData = findViewById(R.id.editTextData)
        textViewStatus = findViewById(R.id.textViewStatus)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not available on this device.", Toast.LENGTH_LONG).show()
            textViewStatus.text = "NFC not supported."
            // finish() // Optionally close the app if NFC is crucial
            return
        }

        if (nfcAdapter?.isEnabled == false) {
            textViewStatus.text = "NFC is disabled. Please enable it in settings."
            Toast.makeText(this, "Please enable NFC.", Toast.LENGTH_LONG).show()
            // Consider prompting user to open NFC settings:
            // startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
        }

        // Create a PendingIntent that will be used to read the tag
        val intent = Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntentFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE // or FLAG_IMMUTABLE if it suits your needs
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        pendingIntent = PendingIntent.getActivity(this, 0, intent, pendingIntentFlag)


        // Setup intent filters for NDEF and Tech discovered.
        // This is for foreground dispatch system.
        val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED).apply {
            try {
                addDataType("*/*") // Handle all NDEF types
            } catch (e: IntentFilter.MalformedMimeTypeException) {
                throw RuntimeException("Failed to add MIME type.", e)
            }
        }
        val tech = IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)
        // We can be more specific for tech, but for writing, Ndef and NdefFormatable are key.
        // The manifest's nfc_tech_filter.xml handles this for when app is not in foreground.

        writingTagFilters = arrayOf(ndef, tech, IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED))
    }

    override fun onResume() {
        super.onResume()
        // Enable foreground dispatch to handle NFC intents when the app is in the foreground
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, writingTagFilters, null)
        // The techList (last param) can be null to listen for all, or specify Ndef::class.java.name
        // e.g., nfcAdapter?.enableForegroundDispatch(this, pendingIntent, writingTagFilters, arrayOf(arrayOf(Ndef::class.java.name)))
        textViewStatus.text = "Approach an NFC tag to write..."
    }

    override fun onPause() {
        super.onPause()
        // Disable foreground dispatch when the app is not in the foreground
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: ${intent.action}")
        textViewStatus.text = "NFC Tag detected! Attempting to write..."

        val tagFromIntent: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)

        if (tagFromIntent == null) {
            textViewStatus.text = "Error: No tag found in intent."
            Toast.makeText(this, "No tag found!", Toast.LENGTH_SHORT).show()
            return
        }

        val dataToWrite = editTextData.text.toString()
        if (dataToWrite.isBlank()) {
            textViewStatus.text = "Please enter data to write."
            Toast.makeText(this, "Data field is empty.", Toast.LENGTH_SHORT).show()
            return
        }

        val ndefMessage = createNdefMessage(dataToWrite)

        if (ndefMessage == null) {
            textViewStatus.text = "Error: Could not create NDEF message."
            Toast.makeText(this, "Failed to create NDEF message.", Toast.LENGTH_SHORT).show()
            return
        }

        writeNdefMessage(tagFromIntent, ndefMessage)
    }


    private fun createNdefMessage(payload: String, lang: String = "en"): NdefMessage? {
        val langBytes = lang.toByteArray(Charset.forName("US-ASCII"))
        val textBytes = payload.toByteArray(Charset.forName("UTF-8"))

        val recordPayload = ByteArray(1 + langBytes.size + textBytes.size)
        recordPayload[0] = langBytes.size.toByte() // Status byte: UTF-8 encoding, language code length

        System.arraycopy(langBytes, 0, recordPayload, 1, langBytes.size)
        System.arraycopy(textBytes, 0, recordPayload, 1 + langBytes.size, textBytes.size)

        val record = NdefRecord(
            NdefRecord.TNF_WELL_KNOWN, // Type Name Format
            NdefRecord.RTD_TEXT,      // Record Type Definition: Text
            ByteArray(0),             // ID (empty for this record)
            recordPayload
        )
        return NdefMessage(arrayOf(record))
        // For other types:
        // NdefRecord.createUri("http://example.com")
        // NdefRecord.createMime("application/vnd.com.example.myapp", myDataBytes)
    }

    private fun writeNdefMessage(tag: Tag, ndefMessage: NdefMessage) {
        try {
            val ndef = Ndef.get(tag)
            if (ndef != null) {
                // Tag is already NDEF formatted
                ndef.connect()
                if (!ndef.isWritable) {
                    textViewStatus.text = "Tag is read-only."
                    Toast.makeText(this, "Tag is read-only.", Toast.LENGTH_SHORT).show()
                    ndef.close()
                    return
                }
                if (ndef.maxSize < ndefMessage.toByteArray().size) {
                    textViewStatus.text = "Tag is too small for this data."
                    Toast.makeText(this, "Data too large for this tag.", Toast.LENGTH_SHORT).show()
                    ndef.close()
                    return
                }
                ndef.writeNdefMessage(ndefMessage)
                textViewStatus.text = "Successfully wrote to NDEF tag!"
                Toast.makeText(this, "Wrote to NDEF tag!", Toast.LENGTH_LONG).show()
                ndef.close()
            } else {
                // Tag is not NDEF formatted, try to format it
                val ndefFormatable = NdefFormatable.get(tag)
                if (ndefFormatable != null) {
                    try {
                        ndefFormatable.connect()
                        ndefFormatable.format(ndefMessage)
                        textViewStatus.text = "Successfully formatted and wrote to tag!"
                        Toast.makeText(this, "Formatted and Wrote to tag!", Toast.LENGTH_LONG).show()
                    } catch (e: IOException) {
                        textViewStatus.text = "Failed to format tag."
                        Toast.makeText(this, "Failed to format tag: ${e.message}", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "Failed to format tag", e)
                    } finally {
                        try {
                            ndefFormatable.close()
                        } catch (e: IOException) {
                            Log.e(TAG, "Error closing NdefFormatable", e)
                        }
                    }
                } else {
                    textViewStatus.text = "Tag is not NDEF writable or formattable."
                    Toast.makeText(this, "Tag is not NDEF compatible.", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) { // Catch FormatException, IOException, etc.
            textViewStatus.text = "Failed to write to tag: ${e.message}"
            Toast.makeText(this, "Write failed: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Write operation failed", e)
        }
    }
}