package com.example.apexercise5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apexercise5.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newArray = intent.getStringArrayListExtra("myArray")

        //Toast.makeText(applicationContext,"${newArray?.size}", Toast.LENGTH_SHORT).show()

        if (newArray != null) {
            binding.txtName.text= newArray.elementAt(0)
            binding.txtContactNumber.text= newArray.elementAtOrNull(1).toString()
            binding.txtEmailAddress.text= newArray.elementAtOrNull(2).toString()
            binding.txtHomeAddress.text= newArray.elementAtOrNull(3).toString()
            binding.txtCity.text= newArray.elementAtOrNull(4).toString()
            binding.txtZipCode.text= newArray.elementAtOrNull(5).toString()
            binding.txtCounty.text= newArray.elementAtOrNull(6).toString()
            binding.txtOrganization.text= newArray.elementAtOrNull(7).toString()
            binding.txtProfession.text= newArray.elementAtOrNull(8).toString()
        }
        //Toast.makeText(applicationContext,"$newArray", Toast.LENGTH_LONG).show()
    }
}