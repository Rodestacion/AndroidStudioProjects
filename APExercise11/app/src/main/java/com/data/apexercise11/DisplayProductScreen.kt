package com.data.apexercise11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.apexercise11.databinding.ActivityDisplayProductScreenBinding

class DisplayProductScreen : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayProductScreenBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView:RecyclerView
    private lateinit var productList: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDisplayProductScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        recyclerView = binding.productRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        productList = getData()
        recyclerView.adapter = ProductAdapter(productList)

    }

    private fun getData():MutableList<Product>{
        return databaseHelper.getAllProducts()
    }
}