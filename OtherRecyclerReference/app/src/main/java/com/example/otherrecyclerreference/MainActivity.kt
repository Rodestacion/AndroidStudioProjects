package com.example.otherrecyclerreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otherrecyclerreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // View Binding
    var binding: ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // getting the employee list by
        // calling getEmployeeData method
        val emplist=Constants.getEmployeeData()
        binding?.rvItemsList?.layoutManager = LinearLayoutManager(this)
        binding?.rvItemsList?.setHasFixedSize(true)

        // Creating an instance of the
        // adapter and passing emplist to it
        val ItemAdapter = ItemAdapter(emplist)

        // Assign ItemAdapter instance to our RecylerView
        binding?.rvItemsList?.adapter = ItemAdapter

        // Applying OnClickListener to our Adapter
        ItemAdapter.setOnClickListener(object :
            ItemAdapter.OnClickListener {
            override fun onClick(position: Int, model: Employee) {
                val intent = Intent(this@MainActivity, EmployeeDetails::class.java)
                // Passing the data to the
                // EmployeeDetails Activity
                intent.putExtra(NEXT_SCREEN, model)
                startActivity(intent)
            }
        })
    }
    companion object{
        val NEXT_SCREEN="details_screen"
    }
}