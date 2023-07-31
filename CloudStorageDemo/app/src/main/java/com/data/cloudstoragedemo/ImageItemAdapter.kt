package com.data.cloudstoragedemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.data.cloudstoragedemo.databinding.ImageItemBinding

class ImageItemAdapter (private val imageItem:MutableList<ImageItem>):RecyclerView.Adapter<ImageItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(inflater,parent,false)
        return ImageItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageItem.size
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        holder.bind(imageItem[position])
    }

}