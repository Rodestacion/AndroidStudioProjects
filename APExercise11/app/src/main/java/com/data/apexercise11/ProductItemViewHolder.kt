package com.data.apexercise11

import androidx.recyclerview.widget.RecyclerView
import com.data.apexercise11.databinding.ProductItemLayoutBinding

class ProductItemViewHolder (private val binding: ProductItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
    fun productBinding(product: Product){
        binding.txtProductName.text = product.name
        binding.txtProductDescription.text = product.description
    }
}