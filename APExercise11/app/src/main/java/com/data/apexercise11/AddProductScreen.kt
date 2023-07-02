package com.data.apexercise11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.data.apexercise11.databinding.ActivityAddProductScreenBinding

class AddProductScreen : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductScreenBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DatabaseHelper(this)

        binding.btnAdd.setOnClickListener {
            
            var name = binding.etAddName.text.toString()
            val description = binding.etAddDescription.text.toString()
            
            if(name.isNotEmpty() && description.isNotEmpty()){
                val addedProduct = Product(0,name,description)

                addData(addedProduct)

                binding.etAddName.text.clear()
                binding.etAddDescription.text.clear()
            }else{
                Toast.makeText(applicationContext, "Please complete all input fields", Toast.LENGTH_SHORT).show()
            }
            
        }

    }

    private fun addData(product: Product){
        databaseHelper.insertNewProduct(product)
        Toast.makeText(applicationContext, "New Product has been added", Toast.LENGTH_SHORT).show()
    }
}