package com.example.uibasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uibasics.databinding.ActivitySample3Binding

class Sample3 : AppCompatActivity() {
    private  lateinit var binding:ActivitySample3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySample3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirm.setOnClickListener {
            val textValue :String = binding.editTxtInput.text.toString()
            binding.txtViewResult.text = textValue
        }
    }
}