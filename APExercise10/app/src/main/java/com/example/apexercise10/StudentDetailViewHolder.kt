package com.example.apexercise10

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.apexercise10.databinding.StudentItemBinding

class StudentDetailViewHolder(private  val binding:StudentItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun studentBind(detail: StudentDetail){
        binding.studentImage.setImageResource(detail.image)
        binding.studentName.text = "Name: ${detail.name}"
        binding.studentAge.text = "Age: ${detail.age}"
        binding.studentGrade.text = "Grade: ${detail.grade}"
    }
}