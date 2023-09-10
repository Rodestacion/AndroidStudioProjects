package com.data.tripmocarrental.borrower

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.res.Resources
import android.icu.text.DateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.data.tripmocarrental.R
import com.data.tripmocarrental.borrower.reservationfragment.CarInfoFragment
import com.data.tripmocarrental.databinding.ActivityCarReservationBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CarReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarReservationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val f1 = CarInfoFragment()

        supportFragmentManager.beginTransaction().apply{
            replace(R.id.reserveFragmentContainerView,f1)
            commit()
        }

    }



}