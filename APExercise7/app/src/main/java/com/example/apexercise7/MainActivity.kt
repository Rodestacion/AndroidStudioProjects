package com.example.apexercise7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgProduct1.setOnClickListener{
            val nextScreen = Intent(this, Product1::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.imgProduct2.setOnClickListener{
            val nextScreen = Intent(this, Product2::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.imgProduct3.setOnClickListener{
            val nextScreen = Intent(this, 3::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.imgProduct4.setOnClickListener{
            val nextScreen = Intent(this, Product4::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.imgProduct5.setOnClickListener{
            val nextScreen = Intent(this, Product5::class.java)
            startActivity(nextScreen)
            finish()
        }


    }
}