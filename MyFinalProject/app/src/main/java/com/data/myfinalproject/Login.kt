package com.data.myfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.myfinalproject.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegBorrower.setOnClickListener {
            val nextScreen = Intent(this,CarBorrowerRegistration::class.java)
            startActivity(nextScreen)
        }

        binding.btnRegOwner.setOnClickListener {
            val nextScreen = Intent(this,CarOwnerRegistration::class.java)
            startActivity(nextScreen)
        }

    }
}