package com.example.apexercise7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise7.databinding.ActivityProduct5Binding

class Product5 : AppCompatActivity() {
    private lateinit var binding: ActivityProduct5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProduct5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            val nextScreen = Intent(this, Product4::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.btnHome.setOnClickListener{
            val nextScreen = Intent(this, MainActivity::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.btnNext.setOnClickListener{
            val nextScreen = Intent(this, Product1::class.java)
            startActivity(nextScreen)
            finish()
        }
    }
}