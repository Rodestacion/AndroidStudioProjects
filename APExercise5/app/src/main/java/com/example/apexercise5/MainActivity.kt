package com.example.apexercise5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apexercise5.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val myIntent = Intent(this, ContactDetailsActivity::class.java)
            val myArray = ArrayList<String>()

            myArray.add(binding.editName.text.toString())
            myArray.add(binding.editContactNumber.text.toString())
            myArray.add(binding.editEmailAddress.text.toString())
            myArray.add(binding.editHomeAddress.text.toString())
            myArray.add(binding.editCity.text.toString())
            myArray.add(binding.editZipCode.text.toString())
            myArray.add(binding.editCounty.text.toString())
            myArray.add(binding.editOrganization.text.toString())
            myArray.add(binding.editProfession.text.toString())

            myIntent.putExtra("myArray",myArray)
            //Toast.makeText(applicationContext,"$myArray", Toast.LENGTH_LONG).show()

            startActivity(myIntent)
            finish() // close the main screen

        }
    }
}