package com.data.mapdemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.data.mapdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGo.setOnClickListener {
            val userLocation = binding.etTextFrom.text.toString()
            val userDestination = binding.etTextTo.text.toString()

            getDirections(userLocation,userDestination)
        }
    }

    private fun getDirections(userLocation: String, userDestination: String) {
        try {
            var uri = Uri.parse("https://www.google.com/maps/dir/$userLocation/$userDestination")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }catch (error:Error){
            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()

        }

    }
}