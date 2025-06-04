package com.data.icebreakinggame

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.data.icebreakinggame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //For Startup Display default image
        startupDisplay()

        //Button to start spinner selection
        binding.breakIceBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Spinner is now selecting", Toast.LENGTH_SHORT).show()
            randomIce()
        }

        //Corresponding dialog message fo Menu bar selection
        binding.materialToolbar.setOnMenuItemClickListener{selectItem->
            when(selectItem.itemId){
                R.id.spinnerMenuBar-> {
                    showSpinnerDialog()
                }
                R.id.aboutMenuBar->{
                    showAboutDialog()
                }
                R.id.privacyPolicy->{
                    showPrivacyDialog()
                }
            }
            true
        }

    }

    private fun showSpinnerDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this,R.style.MyDialogTheme)
        alertDialogBuilder.setTitle("Spinner Image Definition")

        val dialogLayout = layoutInflater.inflate(R.layout.breaking_ice_spinner_layout,null)
        alertDialogBuilder.setIcon(R.drawable.penguin_ice_break_icon)
        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK"){ dialog: DialogInterface, _:Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showPrivacyDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this,R.style.MyDialogTheme)

        alertDialogBuilder.setTitle("Privacy Policy")

        val dialogLayout = layoutInflater.inflate(R.layout.privacy_policy_layout,null)
        alertDialogBuilder.setIcon(R.drawable.penguin_ice_break_icon)
        alertDialogBuilder.setView(dialogLayout)

        //Privacy Policy link to Google Site page
        //val privacyTextView = findViewById<TextView>(R.id.privacyDetailTextView)
        //privacyTextView.movementMethod = LinkMovementMethod.getInstance()

        alertDialogBuilder.setPositiveButton("OK"){ dialog: DialogInterface, _:Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    private fun showAboutDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this,R.style.MyDialogTheme)
        alertDialogBuilder.setTitle("About")
        alertDialogBuilder.setIcon(R.drawable.penguin_ice_break_icon)
        alertDialogBuilder.setMessage("Developer: \n\t\tRodney Estacion\n\nPurpose: \n" +
                "\t\tThis apps serve as an electronic spinner for the game Penguin Breaking Ice or any other similar toy.\n" +
                "\nPenguin Image Source: \n\t\t* Dribble\n" +
                "\t\t" +
                "* creazilla\n\nImage Source URL: \n\t\thttps://dribbble.com\n" +
                "\t\thttps://creazilla.com")

        alertDialogBuilder.setPositiveButton("OK"){ dialog: DialogInterface, _:Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun randomIce() {
        val number = IceNumber(8)
        val breakIce = number.select()

        binding.iceImage.visibility = View.INVISIBLE
        binding.breakIceBtn.visibility = View.INVISIBLE
        binding.penguinAndSpinnerImage.setImageResource(R.drawable.default_spinner)

        //Spinner display
        val firstHandler = Handler(Looper.getMainLooper())
        firstHandler.postDelayed({
            when (breakIce) {
                1 -> binding.penguinAndSpinnerImage.setImageResource(R.drawable.spinner_1)
                2 -> binding.penguinAndSpinnerImage.setImageResource(R.drawable.spinner_2)
                3 -> binding.penguinAndSpinnerImage.setImageResource(R.drawable.spinner_3)
                4 -> binding.penguinAndSpinnerImage.setImageResource(R.drawable.spinner_4)
                5 -> binding.penguinAndSpinnerImage.setImageResource(R.drawable.spinner_5)
                6 -> binding.penguinAndSpinnerImage.setImageResource(R.drawable.spinner_6)
                7 -> binding.penguinAndSpinnerImage.setImageResource(R.drawable.spinner_7)
                8 -> binding.penguinAndSpinnerImage.setImageResource(R.drawable.spinner_8)
            }

        }, 0)
        binding.penguinAndSpinnerImage.visibility = View.VISIBLE

        //Spinner selected output
        val secondHandler = Handler(Looper.getMainLooper())
        secondHandler.postDelayed({
            when (breakIce) {
                1 -> {binding.iceImage.setImageResource(R.drawable.ice_break_1)
                    Toast.makeText(applicationContext,"Break 2 blue ice & 1 white ice!",Toast.LENGTH_LONG).show()}
                2 -> {binding.iceImage.setImageResource(R.drawable.ice_break_2)
                    Toast.makeText(applicationContext,"Break 1 blue ice & 2 white ice!",Toast.LENGTH_LONG).show()}
                3 -> {binding.iceImage.setImageResource(R.drawable.ice_break_3)
                    Toast.makeText(applicationContext,"Skip this turn!",Toast.LENGTH_LONG).show()}
                4 -> {binding.iceImage.setImageResource(R.drawable.ice_break_4)
                    Toast.makeText(applicationContext,"Break 2 blue ice!",Toast.LENGTH_LONG).show()}
                5 -> {binding.iceImage.setImageResource(R.drawable.ice_break_5)
                    Toast.makeText(applicationContext,"Break 2 white ice!",Toast.LENGTH_LONG).show()}
                6 -> {binding.iceImage.setImageResource(R.drawable.ice_break_6)
                    Toast.makeText(applicationContext,"Break 1 blue ice & 1 white ice!",Toast.LENGTH_LONG).show()}
                7 -> {binding.iceImage.setImageResource(R.drawable.ice_break_7)
                    Toast.makeText(applicationContext,"Break 1 white ice!",Toast.LENGTH_LONG).show()}
                8 -> {binding.iceImage.setImageResource(R.drawable.ice_break_8)
                    Toast.makeText(applicationContext,"Break 1 blue ice!",Toast.LENGTH_LONG).show()}
            }
            binding.iceImage.visibility = View.VISIBLE
            binding.breakIceBtn.visibility = View.VISIBLE
            binding.penguinAndSpinnerImage.visibility = View.INVISIBLE
        }, 4000)
    }

    private fun startupDisplay(){
        binding.iceImage.visibility = View.INVISIBLE
        binding.breakIceBtn.visibility = View.INVISIBLE
        binding.penguinAndSpinnerImage.visibility = View.VISIBLE

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            binding.iceImage.visibility = View.VISIBLE
            binding.breakIceBtn.visibility = View.VISIBLE
            binding.penguinAndSpinnerImage.visibility = View.INVISIBLE

            //Set different background color for spinner
            binding.mainScreenLayout.setBackgroundColor(getResources().getColor(R.color.spinner))
        }, 5000)
    }
}