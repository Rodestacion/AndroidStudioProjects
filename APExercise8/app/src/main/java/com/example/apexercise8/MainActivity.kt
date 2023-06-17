package com.example.apexercise8

import android.R
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.apexercise8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var selected:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Checkbox
        binding.chkboxSunny.setOnClickListener {
            if(binding.chkboxSunny.isChecked){
                Toast.makeText(applicationContext, "Sunny", Toast.LENGTH_SHORT).show()
            }
        }

        binding.chkboxCloudy.setOnClickListener {
            if(binding.chkboxCloudy.isChecked){
                Toast.makeText(applicationContext, "Cloudy", Toast.LENGTH_SHORT).show()
            }
        }

        binding.chkboxRainy.setOnClickListener {
            if(binding.chkboxRainy.isChecked){
                Toast.makeText(applicationContext, "Rainy", Toast.LENGTH_SHORT).show()
            }
        }

        //Option Radio Button
        binding.rbnGroupOption.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                binding.radioButton3.id->{ //R.id.radioButton3->{ <<< not working
                    Toast.makeText(applicationContext, "Option 1", Toast.LENGTH_SHORT).show()
                }
                binding.radioButton4.id->{
                    Toast.makeText(applicationContext, "Option 2", Toast.LENGTH_SHORT).show()
                }
            }
        }


        //Location Spinner
        val spinnerItems = arrayOf("New York", "London", "Tokyo")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item,spinnerItems)
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
                selected = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Nothing is selected", Toast.LENGTH_SHORT).show()
            }

        }

        //Frame Layout
        binding.frameLayout.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirm Weather Update")

        var temperatureUnit :String = ""


        if (binding.toggleButton.isChecked){
            temperatureUnit = binding.toggleButton.textOn.toString()
        }else{
            temperatureUnit = binding.toggleButton.textOff.toString()
        }

        var selectedBox = mutableListOf<String>()
        if(binding.chkboxSunny.isChecked){
            selectedBox.add(binding.chkboxSunny.text.toString())
        }

        if(binding.chkboxCloudy.isChecked){
            selectedBox.add(binding.chkboxCloudy.text.toString())
        }

        if(binding.chkboxRainy.isChecked){
            selectedBox.add(binding.chkboxRainy.text.toString())
        }

        var tempString : String = ""
        repeat(selectedBox.size){
            if(selectedBox.size-1>it){
                tempString = "$tempString ${selectedBox.elementAt(it)} / "
            }else{
                tempString = "$tempString ${selectedBox.elementAt(it)} "
            }
        }


        alertDialogBuilder.setMessage("Temperature Unit: $temperatureUnit\nWeather Condition: $tempString \nLocation: $selected")
        alertDialogBuilder.setPositiveButton("OK"){ _: DialogInterface, _:Int ->  // "_" indicate that parameter is not used
            confirmCheckbox()
            Toast.makeText(applicationContext,"Weather Information has been updated", Toast.LENGTH_SHORT).show()
        }

        alertDialogBuilder.setNegativeButton("Cancel"){ _: DialogInterface, _:Int ->  // "_" indicate that parameter is not used
            Toast.makeText(applicationContext,"Weather update Cancelled", Toast.LENGTH_SHORT).show()
        }

        alertDialogBuilder.create().show()


    }

    private fun confirmCheckbox(){
        //Single weather
        if(binding.chkboxSunny.isChecked && !binding.chkboxCloudy.isChecked && !binding.chkboxRainy.isChecked){
            binding.weatherImage.setImageResource(resources.getIdentifier("sunny_weather","drawable",packageName))
        }
        else if(!binding.chkboxSunny.isChecked && binding.chkboxCloudy.isChecked && !binding.chkboxRainy.isChecked){
            binding.weatherImage.setImageResource(resources.getIdentifier("cloudy_weather","drawable",packageName))
        }
        else if(!binding.chkboxSunny.isChecked && !binding.chkboxCloudy.isChecked && binding.chkboxRainy.isChecked){
            binding.weatherImage.setImageResource(resources.getIdentifier("rainy_weather","drawable",packageName))
        }
        //two combination
        else if(binding.chkboxSunny.isChecked && binding.chkboxCloudy.isChecked && !binding.chkboxRainy.isChecked){
            binding.weatherImage.setImageResource(resources.getIdentifier("sunny_cloudy_weather","drawable",packageName))
        }
        else if(binding.chkboxSunny.isChecked && !binding.chkboxCloudy.isChecked && binding.chkboxRainy.isChecked){
            binding.weatherImage.setImageResource(resources.getIdentifier("sunny_rainy_weather","drawable",packageName))
            resources
        }
        else if(!binding.chkboxSunny.isChecked && binding.chkboxCloudy.isChecked && binding.chkboxRainy.isChecked){
            binding.weatherImage.setImageResource(resources.getIdentifier("rainy_cloudy_weather","drawable",packageName))
        }
        //all weather
        else if(binding.chkboxSunny.isChecked && binding.chkboxCloudy.isChecked && binding.chkboxRainy.isChecked){
            binding.weatherImage.setImageResource(resources.getIdentifier("sunny_cloudy_rainy_weather","drawable",packageName))
        }
        //unknown
        else{
            binding.weatherImage.setImageResource(resources.getIdentifier("unknown_weather","drawable",packageName))
        }
    }


}