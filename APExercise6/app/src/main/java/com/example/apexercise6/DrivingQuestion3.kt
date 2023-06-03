package com.example.apexercise6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise6.databinding.ActivityDrivingQuestion3Binding

class DrivingQuestion3 : AppCompatActivity() {
    private lateinit var binding: ActivityDrivingQuestion3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrivingQuestion3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val nextScreen = Intent(this, DrivingQuestion4::class.java)
            startActivity(nextScreen) //display main screen
            finish() // close the main screen
        }
    }
}