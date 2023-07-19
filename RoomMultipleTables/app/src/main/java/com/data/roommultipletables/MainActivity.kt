package com.data.roommultipletables

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.roommultipletables.activity.CustomersActivity
import com.data.roommultipletables.activity.ProductsActivity
import com.data.roommultipletables.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProduct.setOnClickListener {
            val nextScreen = Intent(this,ProductsActivity::class.java)
            startActivity(nextScreen)
        }

        binding.btnCustomer.setOnClickListener {
            val nextScreen = Intent(this,CustomersActivity::class.java)
            startActivity(nextScreen)
        }
    }
}