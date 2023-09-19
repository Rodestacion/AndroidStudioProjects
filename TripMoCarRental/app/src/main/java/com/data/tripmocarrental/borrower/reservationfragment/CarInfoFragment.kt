package com.data.tripmocarrental.borrower.reservationfragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.data.tripmocarrental.databinding.FragmentCarInfoBinding
import com.data.tripmocarrental.dataclass.ReserveInfo
import com.data.tripmocarrental.dataclass.VehicleInfo
import com.google.firebase.firestore.FirebaseFirestore
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CarInfoFragment : Fragment(), DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    private lateinit var binding:FragmentCarInfoBinding

    //Constant Variable
    private val DAY1_INMILLIS: Long = 86400000
    private val HOUR1_INMILLIS: Long = 3600000
    private val MINUTE1_INMILLIS: Long = 60000

    //Date Selection Variable
    private var calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MM/dd/yyy", Locale.TAIWAN)
    private var disableDate = mutableListOf<Calendar>()
    var selectDate:String = ""
    var startDate:String = ""
    var endDate:String = ""

    //Time Selection Variable
    private val timeFormat = SimpleDateFormat("h:mm a", Locale.US)
    var myHour: Int = 0
    var myMinute: Int = 0

    //Invoke variable
    var onBookingCost:((Int)->Unit)?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarInfoBinding.inflate(layoutInflater,container,false)

        var myBundle = arguments
        var selectedVehicle = myBundle?.getStringArrayList("selectedVehicle")
        var userInfo = myBundle?.getStringArrayList("userInfo")
        var ownerInfo = myBundle?.getStringArrayList("ownerInfo")
//        Log.d("VehicleReserveinfo",userInfo.toString())
//        Log.d("VehicleReserveinfo",ownerInfo.toString())

        //initialize text Display Detail
        val carDetail = "${selectedVehicle?.elementAt(1)} / ${selectedVehicle?.elementAt(2)}"
        binding.etCarModelDetail.setText(carDetail)
        binding.etColorReserve.setText(selectedVehicle?.elementAt(3))
        binding.etCapacity.setText(selectedVehicle?.elementAt(4))
        binding.etPlateReserve.setText(selectedVehicle?.elementAt(7))
        binding.etRegisterReserve.setText(selectedVehicle?.elementAt(9))
        binding.etTransmissionType.setText(selectedVehicle?.elementAt(6))
        val location = "${selectedVehicle?.elementAt(12)}, ${selectedVehicle?.elementAt(13)}"
        binding.etLocation.setText(location)

        if(selectedVehicle?.elementAt(10)=="Borrower Self Drive"){
            binding.btnRadSelfDriveReserve.isEnabled = false
            binding.btnRadOwnerDriveReserve.isEnabled = false

            binding.btnRadSelfDriveReserve.isChecked = true

            if(!(userInfo?.elementAt(7).toBoolean())){
                binding.btnBookingCost.isEnabled = false
                binding.btnAvailableDate.isEnabled = false
                binding.btnTimePickReturn.isEnabled = false
                binding.btnBookingCost.text = "Sorry! License in Required!"
            }

        }else if(selectedVehicle?.elementAt(10)=="Drive by Owner"){
            binding.btnRadSelfDriveReserve.isEnabled = false
            binding.btnRadOwnerDriveReserve.isEnabled = false

            binding.btnRadOwnerDriveReserve.isChecked = true
        }

        //binding.etCarModelDetail.setText("")



        //initialize date disable
        //to change from FireStore Array
        disableDate = getDateDisable(selectedVehicle!!.elementAt(0))

        binding.btnAvailableDate.setOnClickListener {
            startDate =""
            endDate = ""
            selectDate = "Start"
            customStartCalendar()
        }

        binding.btnTimePickReturn.setOnClickListener {
            customTimePicker()
        }
        binding.btnBookingCost.setOnClickListener {
            if(
                binding.etStartDate.text!!.isEmpty() ||
                binding.etEndDate.text!!.isEmpty() ||
                binding.etTimePickReturn.text!!.isEmpty() ||
                !(binding.btnRadOwnerDriveReserve.isChecked || binding.btnRadSelfDriveReserve.isChecked)

            ){
                Toast.makeText(requireContext(), "Filled up the empty field with necessary information", Toast.LENGTH_SHORT).show()
            }else{
                var bookInfo = arrayListOf<String>()
                bookInfo.add(binding.etStartDate.text.toString())
                bookInfo.add(binding.etEndDate.text.toString())
                bookInfo.add(binding.etTimePickReturn.text.toString())

                val drivingMode = if(binding.btnRadOwnerDriveReserve.isChecked){
                    binding.btnRadOwnerDriveReserve.text.toString()
                }else {
                    binding.btnRadSelfDriveReserve.text.toString()
                }
                bookInfo.add(drivingMode)

                //Log.d("VehicleCostA",bookInfo.toString())
                setFragmentResult("requestKey", bundleOf("bookInfoKey" to bookInfo))
                onBookingCost?.invoke(0)
            }
        }


        return binding.root
    }

    private fun getDateDisable(searchItem: String): MutableList<Calendar> {
        var disableDates = arrayListOf<String>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("disabledDate")
        var finalData = mutableListOf<Calendar>()
        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data

                    if(details["vehicleID"].toString()==searchItem) {
                        val newDateList = details["disableDate"]
                        Log.d("Vehicle1",newDateList.toString())
                        disableDates= newDateList as ArrayList<String>
                    }
                }
                var count =0

                while (count <disableDates.size){
                    finalData.add(convertDate(disableDates.elementAt(count)))
                    count++
                }
            }

        Handler().postDelayed({
            Log.d("Vehicle2",finalData.toString())
        },1000)

        return finalData
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
    private fun customTimePicker(){
        var dialog = TimePickerDialog(
            this.requireContext(),
            this,
            myHour,
            myMinute,
            false
        )
        dialog.show()
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

                    if(disableDate.size>0){
                        while (count<disableDate.size){
                            if(convertDate(startDate)<disableDate[count] && disableDate[count]<convertDate(endDate)){
                                reserveNotPossible = true
                            }
                            count++
                        }
                    }

                    if(reserveNotPossible){
                        startDate = ""
                        endDate = ""
                        selectDate = ""
                        Toast.makeText(requireContext(), "Selected Date Range is not available", Toast.LENGTH_SHORT).show()
                    }else{
                        binding.etStartDate.setText(startDate)
                        binding.etEndDate.setText(endDate)
                        startDate = ""
                        endDate = ""
                        selectDate = ""
                    }
                }else{
                    binding.etStartDate.setText("")
                    binding.etEndDate.setText("")
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

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        val newTime = (myHour*HOUR1_INMILLIS) + (myMinute*MINUTE1_INMILLIS)
        displayFormattedTime(newTime)
    }

    private fun displayFormattedTime(newTime: Long) {
        val myTime = Calendar.getInstance()
        myTime.timeInMillis = newTime - (8*HOUR1_INMILLIS) // Remove time zone offset
        binding.etTimePickReturn.setText("${timeFormat.format(myTime.time)}")
    }

    //End Function for Date Selection

}