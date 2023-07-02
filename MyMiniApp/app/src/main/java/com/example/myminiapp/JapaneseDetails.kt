package com.example.myminiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myminiapp.databinding.ActivityJapaneseDetailsBinding

class JapaneseDetails : AppCompatActivity() {
    private lateinit var binding: ActivityJapaneseDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJapaneseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var arrayDetails = intent.getStringArrayListExtra("word")
        if (arrayDetails != null) {
            binding.txtEnglish.text=arrayDetails.elementAt(0)
            binding.txtJapanese.text=arrayDetails.elementAt(1)
            binding.txtDetails.text=arrayDetails.elementAt(2)
        }
    }
}