package com.data.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.data.viewmodeldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var viewModel:ViewModelProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewModel  = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.textView.text = viewModel.myNumber.toString()


        binding.button.setOnClickListener{
            viewModel.addNumber()
            binding.textView.text = viewModel.myNumber.toString()
        }


    }
}