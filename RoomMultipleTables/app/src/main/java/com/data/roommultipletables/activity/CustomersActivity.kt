package com.data.roommultipletables.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.data.roommultipletables.database.AppDatabase
import com.data.roommultipletables.databinding.ActivityCustomersBinding
import com.data.roommultipletables.model.Customers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomersActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCustomersBinding
    private lateinit var appDb: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = AppDatabase.invoke(this)

        //create
        binding.btnCSave.setOnClickListener {
            var name = binding.etCName.text.toString()
            var details = binding.etCDetails.text.toString()
            val customer = Customers(0,name,details)
            save(customer)
            view()
        }

        //read
        binding.btnCView.setOnClickListener {
            view()
        }

        binding.btnCUpdate.setOnClickListener {
            var id = binding.etCId.text.toString().toInt()
            var name = binding.etCName.text.toString()
            var details = binding.etCDetails.text.toString()
            val customer = Customers(id,name,details)
            update(customer)

        }
        binding.btnCDelete.setOnClickListener {
            var id = binding.etCId.text.toString().toInt()
            var name = binding.etCName.text.toString()
            var details = binding.etCDetails.text.toString()
            val customer = Customers(id,name,details)
            delete(customer)
        }

    }

    //Create
    private fun save(customers: Customers){
        GlobalScope.launch ( Dispatchers.IO ){
            appDb.getCustomers().addCustomer(customers)
        }
        Toast.makeText(applicationContext, "Saved!", Toast.LENGTH_SHORT).show()
    }

    //read
    private fun view(){
        lateinit var customers: List<Customers>
        GlobalScope.launch ( Dispatchers.IO ){
            customers = appDb.getCustomers().getAllCustomers()
            withContext(Dispatchers.Main){
                Toast.makeText(applicationContext, customers.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun update(customers: Customers){
        GlobalScope.launch ( Dispatchers.IO ){
            appDb.getCustomers().updateCustomer(customers)
        }
        Toast.makeText(applicationContext, "Updated!", Toast.LENGTH_SHORT).show()
    }

    private fun delete(customers: Customers){
        GlobalScope.launch ( Dispatchers.IO ){
            appDb.getCustomers().deleteCustomer(customers)
        }
        Toast.makeText(applicationContext, "Deleted!", Toast.LENGTH_SHORT).show()
    }
}