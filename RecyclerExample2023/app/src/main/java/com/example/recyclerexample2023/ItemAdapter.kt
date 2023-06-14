package com.example.recyclerexample2023

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerexample2023.databinding.ItemLayoutBinding

class ItemAdapter(private val items:List<Item>):RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater,parent,false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            //Toast.makeText(holder.itemView.context, items[position].name, Toast.LENGTH_SHORT).show()
            var myIntent = Intent(holder.itemView.context,ItemInfoActivity::class.java)
            myIntent.putExtra("name",items[position].name)

            //holder.itemView.context
            holder.itemView.context.startActivity(myIntent)
        }
    }

}