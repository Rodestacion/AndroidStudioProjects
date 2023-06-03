package com.example.apexercise6

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apexercise6.databinding.ActivityExaminationResultBinding
import java.util.ArrayList

class ExaminationResult : AppCompatActivity() {
    private lateinit var binding: ActivityExaminationResultBinding
    private val masterAnswer = arrayListOf<String>("Private vehicles","Quickly","Stop and proceed if there is no danger","A privilege","All answers are correct","Look around first","The road has two or more lanes going in one direction","Use the rear view mirror to check the vehicle you have overtaken","All answers are applicable","17 years old")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExaminationResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val myAnswer = intent.getStringArrayListExtra("myAnswer")

        if (myAnswer != null) {
            binding.txtScore.text = countCorrect(myAnswer).toString()

            binding.txtAnswer1.text=myAnswer.elementAt(0)
            binding.txtAnswer1.setTextColor(Color.parseColor(checkCorrect(myAnswer,0)))
            binding.txtAnswer2.text=myAnswer.elementAt(1)
            binding.txtAnswer2.setTextColor(Color.parseColor(checkCorrect(myAnswer,1)))
            binding.txtAnswer3.text=myAnswer.elementAt(2)
            binding.txtAnswer3.setTextColor(Color.parseColor(checkCorrect(myAnswer,2)))
            binding.txtAnswer4.text=myAnswer.elementAt(3)
            binding.txtAnswer4.setTextColor(Color.parseColor(checkCorrect(myAnswer,3)))
            binding.txtAnswer5.text=myAnswer.elementAt(4)
            binding.txtAnswer5.setTextColor(Color.parseColor(checkCorrect(myAnswer,4)))
            binding.txtAnswer6.text=myAnswer.elementAt(5)
            binding.txtAnswer6.setTextColor(Color.parseColor(checkCorrect(myAnswer,5)))
            binding.txtAnswer7.text=myAnswer.elementAt(6)
            binding.txtAnswer7.setTextColor(Color.parseColor(checkCorrect(myAnswer,6)))
            binding.txtAnswer8.text=myAnswer.elementAt(7)
            binding.txtAnswer8.setTextColor(Color.parseColor(checkCorrect(myAnswer,7)))
            binding.txtAnswer9.text=myAnswer.elementAt(8)
            binding.txtAnswer9.setTextColor(Color.parseColor(checkCorrect(myAnswer,8)))
            binding.txtAnswer10.text=myAnswer.elementAt(9)
            binding.txtAnswer10.setTextColor(Color.parseColor(checkCorrect(myAnswer,9)))
        }

    }
    private fun checkCorrect(newArray: ArrayList<String>, num:Int):String{
        var color:String = ""
        if(newArray.elementAt(num)==masterAnswer.elementAt(num)){
            color = "#1134A6"
        }else{
            color = "#a01820"
        }

        return color
    }

    private fun countCorrect (newArray: ArrayList<String>):Int{
        var score:Int = 0

        repeat(newArray.size){
            if(newArray.elementAt(it)==masterAnswer.elementAt(it)){
                score++
            }
        }

        return score
    }
}