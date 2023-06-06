package com.example.apexercise7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise7.databinding.ActivityProduct4Binding

class Product4 : AppCompatActivity() {
    private lateinit var binding: ActivityProduct4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProduct4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            val nextScreen = Intent(this, Product3::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.btnHome.setOnClickListener{
            val nextScreen = Intent(this, MainActivity::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.btnNext.setOnClickListener{
            val nextScreen = Intent(this, Product5::class.java)
            startActivity(nextScreen)
            finish()
        }
    }
}