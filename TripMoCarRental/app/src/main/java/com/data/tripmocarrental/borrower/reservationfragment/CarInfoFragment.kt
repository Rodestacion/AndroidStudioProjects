package com.data.tripmocarrental.borrower.reservationfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.data.tripmocarrental.databinding.FragmentCarInfoBinding
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CarInfoFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding:FragmentCarInfoBinding
    private val DAY1_INMILLIS: Long = 86400000

    //Date Selection Variable
    private var calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MM/dd/yyy", Locale.TAIWAN)
    private var disableDate = mutableListOf<Calendar>()
    var selectDate:String = ""
    var startDate:String = ""
    var endDate:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarInfoBinding.inflate(layoutInflater,container,false)

        //initialize date disable
        disableDate.add(convertDate("09/01/2023"))
        disableDate.add(convertDate("09/08/2023"))
        disableDate.add(convertDate("09/15/2023"))
        disableDate.add(convertDate("09/21/2023"))
        disableDate.add(convertDate("09/28/2023"))

        binding.carAvailableCalendarView.apply {
//            val todayDate = Calendar.getInstance()
//            minDate = (todayDate.timeInMillis) + (2*DAY1_INMILLIS)
//
//            setDate(todayDate.timeInMillis,false)

        }
        binding.button.setOnClickListener {
            startDate =""
            endDate = ""
            selectDate = "Start"
            customStartCalendar()
        }


        return binding.root
    }

    private fun customStartCalendar(){
        val dateOffset= Calendar.getInstance().timeInMillis + (DAY1_INMILLIS*3)
        val newStart = Calendar.getInstance()
        newStart.timeInMillis = dateOffset

        calendar = Calendar.getInstance()

        var dialog = DatePickerDialog.newInstance(
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.apply {
            setTitle("Borrow Start Date")
            minDate = newStart
            if (disableDate!=null){
                disabledDays= disableDate.toTypedArray()
            }
        }
        dialog.show(childFragmentManager,"Datepickerdialog")

    }

    private fun customEndCalendar(){
        if (selectDate == "End"){
            val date1= startDate
            val myMaxDate = convertDate(startDate).timeInMillis + (DAY1_INMILLIS*30)
            val newRange = Calendar.getInstance()
            newRange.timeInMillis = myMaxDate

            var dialog = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            var highlights = mutableListOf<Calendar>()
            highlights.add(convertDate(startDate))
            dialog.apply {
                setTitle("Borrow End Date")
                minDate = convertDate(date1)
                maxDate = newRange
                if (disableDate != null) {
                    disabledDays = disableDate.toTypedArray()
                    highlightedDays = highlights.toTypedArray()
                }

            }
            dialog.show(childFragmentManager,"Datepickerdialog")
        }
    }

    private fun convertDate(dateValue:String):Calendar {
        val dateFormat = SimpleDateFormat("MM/dd/yyy")
        val myDate:Date = dateFormat.parse(dateValue) as Date
        var myCalendar = Calendar.getInstance()

        myCalendar.time = myDate
        return myCalendar
    }

    //Function for Date Selection
    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        calendar.set(year,monthOfYear,dayOfMonth)
        displayFormattedDate(calendar.timeInMillis)
    }

    private fun displayFormattedDate(timestamp:Long){
            if (selectDate == "Start"){
                startDate = formatter.format(timestamp)
                selectDate = "End"
                customEndCalendar()

            }else if(selectDate =="End"){
                endDate = formatter.format(timestamp)

                if(startDate != endDate){
                    var count:Int = 0
                    var reserveNotPossible = false
                    while (count<disableDate.size){
                        if(convertDate(startDate)<disableDate[count] && disableDate[count]<convertDate(endDate)){
                            reserveNotPossible = true
                        }
                        count++
                    }

                    if(reserveNotPossible){
                        binding.etCheckResult.setText("")
                        startDate = ""
                        endDate = ""
                        selectDate = ""
                        Toast.makeText(requireContext(), "Selected Date Range is not available", Toast.LENGTH_SHORT).show()
                    }else{
                        binding.etCheckResult.setText("$startDate $endDate")
                        startDate = ""
                        endDate = ""
                        selectDate = ""
                    }
                }else{
                    binding.etCheckResult.setText("")
                    startDate = ""
                    endDate = ""
                    selectDate = ""
                    Toast.makeText(requireContext(), "Date must not same", Toast.LENGTH_SHORT).show()
                }
            }else{
                startDate = ""
                endDate = ""
                selectDate = ""
            }
    }

    //End Function for Date Selection

}