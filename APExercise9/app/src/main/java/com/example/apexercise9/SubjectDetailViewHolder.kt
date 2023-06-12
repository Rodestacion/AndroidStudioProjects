package com.example.apexercise9

import androidx.recyclerview.widget.RecyclerView
import com.example.apexercise9.databinding.SubjectLayoutBinding

class SubjectDetailViewHolder(private val binding: SubjectLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun detailBind(details:SubjectDetail){
        binding.professorImage.setImageResource(details.image)
        binding.subjectName.text = "Subject: ${details.subject}"
        binding.subjectSchedule.text = "Schedule: ${details.schedule}"
        binding.professorName.text = "Professor: ${details.professorName}"
    }
}