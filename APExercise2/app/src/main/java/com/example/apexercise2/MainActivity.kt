package com.example.apexercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val result1: String = binding.inputText1.text.toString()
            val result2: String = binding.inputText2.text.toString()

            binding.txtResult.text = "The sum is: " + (result1.toDouble()+result2.toDouble()).toString()
        }

        binding.btnSubtract.setOnClickListener {
            val result1: String = binding.inputText1.text.toString()
            val result2: String = binding.inputText2.text.toString()

            binding.txtResult.text = "The difference is: " + (result1.toDouble()-result2.toDouble()).toString()
        }

        binding.btnMultiply.setOnClickListener {
            val result1: String = binding.inputText1.text.toString()
            val result2: String = binding.inputText2.text.toString()

            binding.txtResult.text = "The product is: " + (result1.toDouble()*result2.toDouble()).toString()
        }

        binding.btnDivide.setOnClickListener {
            val result1: String = binding.inputText1.text.toString()
            val result2: String = binding.inputText2.text.toString()

            binding.txtResult.text = "The quotient is: " + (result1.toDouble()/result2.toDouble()).toString()
        }

    }
}