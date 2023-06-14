package com.example.recyclerexample2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerexample2023.databinding.ActivityItemInfoBinding

class ItemInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtItemInfo.text = intent.getStringExtra("name")
    }
}