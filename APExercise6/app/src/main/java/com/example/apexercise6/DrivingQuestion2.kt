package com.example.apexercise6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise6.databinding.ActivityDrivingQuestion2Binding

class DrivingQuestion2 : AppCompatActivity() {
    private lateinit var binding: ActivityDrivingQuestion2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrivingQuestion2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val nextScreen = Intent(this, DrivingQuestion3::class.java)
            startActivity(nextScreen) //display main screen
            finish() // close the main screen
        }
    }
}