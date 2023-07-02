package com.example.myminiapp

import androidx.recyclerview.widget.RecyclerView
import com.example.myminiapp.databinding.JapaneseItemBinding

class JapaneseItemViewHolder(private  val binding:JapaneseItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun wordBinding(word:Japanese){
        binding.englishText.text = word.english
        binding.japaneseText.text = word.japanese
    }
}