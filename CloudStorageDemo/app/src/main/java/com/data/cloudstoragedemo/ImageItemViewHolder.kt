package com.data.cloudstoragedemo

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.data.cloudstoragedemo.databinding.ImageItemBinding

class ImageItemViewHolder (private val binding: ImageItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(imageItem:ImageItem){
        binding.recyclerTextView.text = imageItem.imageDescription
        //binding.recyclerImageItem.setImageURI(imageItem.imageLink.toUri())
        Glide.with(binding.root)
            .load(imageItem.imageLink)
            .apply(RequestOptions.overrideOf(300,300))
            .into(binding.recyclerImageItem)
    }
}