package com.example.intentactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentactivity.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("myName")
        binding.txtWelcome.text = "Hello $name"

    }
}