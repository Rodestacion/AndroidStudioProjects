package com.example.apexercise9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apexercise9.databinding.SubjectLayoutBinding

class SubjectDetailAdapter(private val subject:List<SubjectDetail>):RecyclerView.Adapter<SubjectDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectDetailViewHolder {
        val  inflater = LayoutInflater.from(parent.context)
        val binding = SubjectLayoutBinding.inflate(inflater,parent,false)
        return SubjectDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subject.size
    }

    override fun onBindViewHolder(holder: SubjectDetailViewHolder, position: Int) {
        holder.detailBind(subject[position])
    }
}