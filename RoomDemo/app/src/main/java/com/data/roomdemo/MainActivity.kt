package com.data.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Toast
import com.data.roomdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var appDb:ProductsDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = ProductsDatabase.invoke(this)

        //create
        binding.btnSave.setOnClickListener {
            var name = binding.etName.text.toString()
            var price = binding.etPrice.text.toString().toInt()
            val product = Products(0,name,price)
            save(product)
            view()



        }

        //read
        binding.btnView.setOnClickListener {
            view()
        }

        binding.btnUpdate.setOnClickListener {
            var id = binding.etID.text.toString().toInt()
            var name = binding.etName.text.toString()
            var price = binding.etPrice.text.toString().toInt()
            val product = Products(id,name,price)
            update(product)

        }
        binding.btnDelete.setOnClickListener {
            var id = binding.etID.text.toString().toInt()
            var name = binding.etName.text.toString()
            var price = binding.etPrice.text.toString().toInt()
            val product = Products(id,name,price)
            delete(product)
        }

    }

    private fun clear(){
        binding.etName.text.clear()
        binding.etPrice.text.clear()
        binding.etID.text.clear()
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