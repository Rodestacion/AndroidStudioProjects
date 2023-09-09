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

class CarReservationActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityCarReservationBinding

    //Date Selection Variable
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MM/dd/yyy", Locale.TAIWAN)

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val f1 = CarInfoFragment()

        supportFragmentManager.beginTransaction().apply{
            replace(R.id.reserveFragmentContainerView,f1)
        }



//        binding.etStartDateRange.setOnClickListener {
//            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
////            datePicker.apply {
////                dis
////            }
//            datePicker.show(supportFragmentManager,"DatePicker")
//
//            datePicker.addOnPositiveButtonClickListener {datePicked->
//                val startDate = datePicked.first
//                val endDate = datePicked.second
//                displayFormattedDate(startDate,endDate)
//                //Toast.makeText(this, "${datePicker.headerText} is selected", Toast.LENGTH_SHORT).show()
//            }
//
//            datePicker.addOnNegativeButtonClickListener {
//                Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_SHORT).show()
//            }
//
//            datePicker.addOnCancelListener {
//                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_SHORT).show()
//            }
//
//
//
//        }
//
//        binding.etStartTimeRange.setOnClickListener {
//            val timePickerDialog = TimePickerDialog(this,this,hour,minute,false)
//            timePickerDialog.show()
//            timePickerDialog.findViewById<View>(Resources.getSystem().getIdentifier("minutes","id","android")).visibility = View.GONE
//            timePickerDialog.findViewById<View>(Resources.getSystem().getIdentifier("separator","id","android")).visibility = View.INVISIBLE
//            val delegatefield = timePickerDialog.javaClass.getDeclaredField("mDelegate")
//            delegatefield.isAccessible = true
//            val autoAdvance = delegatefield.get(timePickerDialog)
//            val     autoadvanceField = autoAdvance.javaClass.getDeclaredField("mAllowAutoAdvance")
//
//            autoadvanceField.isAccessible = true
//            autoadvanceField.set(autoAdvance,false)
        //}



    }

//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        calendar.set(year,month,dayOfMonth)
//        displayFormattedDate(calendar.timeInMillis)
//    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        binding.etStartTimeRange.setText (" $myHour  ")
//        " + "Month: " + $myMonth + "
//        " + "Day: " + $myDay + "
//        " + "Hour: " + $myHour + "
//        " + "Minute: " + $myMinute
    }

    private fun displayFormattedDate(timestamp1:Long,timestamp2:Long){
        binding.etStartDateRange.setText(formatter.format(timestamp1))
        binding.etEndDateRange.setText(formatter.format(timestamp2))
    }
    //End Function for Date Selection
}