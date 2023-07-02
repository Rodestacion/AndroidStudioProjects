package com.data.apexercise11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.apexercise11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DatabaseHelper(this)

        binding.btnViewProducts.setOnClickListener {
            val nextScreen = Intent(this,DisplayProductScreen::class.java)
            startActivity(nextScreen)
        }

        binding.btnAddProduct.setOnClickListener {
            val nextScreen = Intent(this,AddProductScreen::class.java)
            startActivity(nextScreen)
        }


    }
}