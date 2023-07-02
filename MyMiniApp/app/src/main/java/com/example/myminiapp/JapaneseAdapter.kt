package com.example.myminiapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myminiapp.databinding.JapaneseItemBinding

class JapaneseAdapter(private val japanese:List<Japanese>):RecyclerView.Adapter<JapaneseItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JapaneseItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JapaneseItemBinding.inflate(inflater,parent,false)
        return JapaneseItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return japanese.size
    }

    override fun onBindViewHolder(holder: JapaneseItemViewHolder, position: Int) {
        holder.wordBinding(japanese[position])

        holder.itemView.setOnClickListener {
            var myIntent = Intent(holder.itemView.context,JapaneseDetails::class.java)
            var myArray = arrayListOf<String>()
            myArray.add(japanese[position].english)
            myArray.add(japanese[position].japanese)
            myArray.add(japanese[position].pronunciation)

            myIntent.putStringArrayListExtra("word",myArray)
            holder.itemView.context.startActivity(myIntent)

        }
    }
}