package com.example.apexercise6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnGo.setOnClickListener {
            val nextScreen = Intent(this, DrivingQuestion1::class.java)
            startActivity(nextScreen) //display main screen
            finish() // close the main screen
        }
    }
}