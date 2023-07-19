package com.data.roommultipletables.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.data.roommultipletables.database.AppDatabase
import com.data.roommultipletables.databinding.ActivityProductsBinding
import com.data.roommultipletables.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProductsBinding
    private lateinit var appDb: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = AppDatabase.invoke(this)

        //create
        binding.btnSave.setOnClickListener {
            var name = binding.etName.text.toString()
            var details = binding.etDetails.text.toString()
            val product = Products(0,name,details)
            save(product)
            view()
        }

        //read
        binding.btnView.setOnClickListener {
            view()
        }

        binding.btnUpdate.setOnClickListener {
            var id = binding.etId.text.toString().toInt()
            var name = binding.etName.text.toString()
            var details = binding.etDetails.text.toString()
            val product = Products(id,name,details)
            update(product)

        }
        binding.btnDelete.setOnClickListener {
            var id = binding.etId.text.toString().toInt()
            var name = binding.etName.text.toString()
            var details = binding.etDetails.text.toString()
            val product = Products(id,name,details)
            delete(product)
        }

    }

    //Create
    private fun save(products: Products){
        GlobalScope.launch ( Dispatchers.IO ){
            appDb.getProducts().addProduct(products)
        }
        Toast.makeText(applicationContext, "Saved!", Toast.LENGTH_SHORT).show()
    }

    //read
    private fun view(){
        lateinit var products: List<Products>
        GlobalScope.launch ( Dispatchers.IO ){
            products = appDb.getProducts().getAllProducts()
            withContext(Dispatchers.Main){
                Toast.makeText(applicationContext, products.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun update(products: Products){
        GlobalScope.launch ( Dispatchers.IO ){
            appDb.getProducts().updateProduct(products)
        }
        Toast.makeText(applicationContext, "Updated!", Toast.LENGTH_SHORT).show()
    }

    private fun delete(products: Products){
        GlobalScope.launch ( Dispatchers.IO ){
            appDb.getProducts().deleteProduct(products)
        }
        Toast.makeText(applicationContext, "Deleted!", Toast.LENGTH_SHORT).show()
    }
}