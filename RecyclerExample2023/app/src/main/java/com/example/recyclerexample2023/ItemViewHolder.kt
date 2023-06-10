package com.example.recyclerexample2023

import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerexample2023.databinding.ItemLayoutBinding

class ItemViewHolder(private val binding:ItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind (item:Item){
        binding.txtRecyclerName.text = item.name
        binding.txtRecyclerDesc.text = item.description
    }
}