package com.example.apexercise10

import android.content.Intent
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.apexercise10.databinding.ActivityMainBinding
import com.example.apexercise10.databinding.StudentItemBinding


class StudentDetailAdapter(private val student: List<StudentDetail>):RecyclerView.Adapter<StudentDetailViewHolder>() {
     private lateinit var binding2:ActivityMainBinding
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StudentItemBinding.inflate(inflater,parent,false)
        return StudentDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return student.size
    }

    override fun onBindViewHolder(holder: StudentDetailViewHolder, position: Int) {
        holder.studentBind(student[position])
        
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${student[position].name}\n${student[position].address}", Toast.LENGTH_SHORT).show()
        }


    }
    
}