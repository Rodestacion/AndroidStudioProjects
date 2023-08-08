package com.data.myfinalproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.data.myfinalproject.databinding.ActivityCarOwnerRegistrationBinding
import java.time.Month
import java.time.Year

class CarOwnerRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityCarOwnerRegistrationBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarOwnerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSpinnerDate()


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setSpinnerDate(){
        var tempYear = Year.now().toString().toInt()
        var yearList = mutableListOf<String>()

        yearList.add("-- Year --")
        while (tempYear>=1900){
            yearList.add(tempYear.toString())
            tempYear--
        }

        var tempDay:Int = 1
        var dayList = mutableListOf<String>()
        dayList.add("-- Day --")
        while (tempDay<=31){
            dayList.add(tempDay.toString())
            tempDay++
        }

        val monthList = listOf("-- Month --","January","February","March","April","May","June","July","August","September","October","November","December")

        val spinnerAdapter1= ArrayAdapter(this,android.R.layout.simple_spinner_item,yearList)
        spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerYear.adapter = spinnerAdapter1

        val spinnerAdapter2= ArrayAdapter(this,android.R.layout.simple_spinner_item,dayList)
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDay.adapter = spinnerAdapter2

        val spinnerAdapter3= ArrayAdapter(this,android.R.layout.simple_spinner_item,monthList)
        spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMonth.adapter = spinnerAdapter3
        


    }
}