package com.example.apexercise6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.apexercise6.databinding.ActivityDrivingQuestion7Binding

class DrivingQuestion7 : AppCompatActivity() {
    private lateinit var binding: ActivityDrivingQuestion7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrivingQuestion7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val myAnswer = intent.getStringArrayListExtra("myAnswer")

        binding.btnBack.setOnClickListener {
            Toast.makeText(applicationContext,"Sorry! You cannot go back in previous screen", Toast.LENGTH_SHORT).show()
        }

        binding.btnNext.setOnClickListener {

            if(binding.radGroupAnswer.checkedRadioButtonId<0){
                Toast.makeText(applicationContext,"No answer has been selected", Toast.LENGTH_SHORT).show()
            }else{
                var selectedButtonID: Int = binding.radGroupAnswer.checkedRadioButtonId
                val selectedButton: RadioButton = findViewById(selectedButtonID)
                val selectedAnswer:String = selectedButton.text.toString()

                val nextScreen = Intent(this, DrivingQuestion8::class.java)
                myAnswer?.add(selectedAnswer)

                nextScreen.putExtra("myAnswer",myAnswer)

                startActivity(nextScreen) //display main screen
                finish() // close the main screen

            }

        }
    }
}