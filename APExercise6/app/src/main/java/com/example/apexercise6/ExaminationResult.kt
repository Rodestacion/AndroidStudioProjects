package com.example.apexercise6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise6.databinding.ActivityExaminationResultBinding

class ExaminationResult : AppCompatActivity() {
    private lateinit var binding: ActivityExaminationResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExaminationResultBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}