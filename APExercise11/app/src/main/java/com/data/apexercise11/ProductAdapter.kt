package com.data.apexercise11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.data.apexercise11.databinding.ProductItemLayoutBinding

class ProductAdapter(private val product: List<Product>):RecyclerView.Adapter<ProductItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemLayoutBinding.inflate(inflater,parent,false)
        return ProductItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.productBinding(product[position])
    }
}