package com.example.apexercise6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise6.databinding.ActivityDrivingQuestion6Binding

class DrivingQuestion6 : AppCompatActivity() {
    private lateinit var binding: ActivityDrivingQuestion6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrivingQuestion6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val nextScreen = Intent(this, DrivingQuestion7::class.java)
            startActivity(nextScreen) //display main screen
            finish() // close the main screen
        }
    }
}