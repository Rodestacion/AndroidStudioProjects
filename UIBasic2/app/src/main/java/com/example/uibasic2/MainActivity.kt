package com.example.uibasic2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.uibasic2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Toggle Button
        binding.toggleButton.setOnClickListener {
            if(binding.toggleButton.isChecked){
                Toast.makeText(applicationContext, "On", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Off", Toast.LENGTH_SHORT).show()
            }
        }

        //Radio button

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radioButton3->{
                    Toast.makeText(applicationContext, "Radio Button 3", Toast.LENGTH_SHORT).show()
                }
                R.id.radioButton4->{
                    Toast.makeText(applicationContext, "Radio Button 4", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Checkbox
        binding.checkBox.setOnClickListener {
            if(binding.checkBox.isChecked){
                Toast.makeText(applicationContext, "Checked!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Uncghecked", Toast.LENGTH_SHORT).show()
            }
        }

        //progress bar
        binding.button.setOnClickListener {
            binding.progressBar.progress +=10
        }

        //Spinner
        val spinnerItems = arrayOf("Item 1", "Item 2", "Item 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter =adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = spinnerItems[position]
                Toast.makeText(applicationContext, selectedItem, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Nothing is selected", Toast.LENGTH_SHORT).show()
            }

        }

        //custom spinner
        val adapter2 = ArrayAdapter(this,R.layout.custom_item,spinnerItems)
        adapter2.setDropDownViewResource(R.layout.custom_item)
        binding.spinner2.adapter =adapter2

        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = spinnerItems[position]
                Toast.makeText(applicationContext, selectedItem, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Nothing is selected", Toast.LENGTH_SHORT).show()
            }

        }



    }
}