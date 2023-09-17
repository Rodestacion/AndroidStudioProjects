package com.data.tripmocarrental.borrower.reservationfragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.data.tripmocarrental.databinding.FragmentCarCostBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class CarCostFragment : Fragment() {
    private lateinit var binding: FragmentCarCostBinding
    var startDate:String = ""
    var endDate:String = ""
    var pickupTime:String = ""
    private val DAY1_INMILLIS: Long = 86400000

    //Invoke variable
    var onBookingNow:((Int)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarCostBinding.inflate(layoutInflater,container,false)

        var myBundle = arguments
        var reserveInfo = myBundle?.getStringArrayList("bookInfoKey")
        val selectedVehicle = myBundle?.getStringArrayList("selectedVehicle")
        val userInfo = myBundle?.getStringArrayList("userInfo")
        val ownerInfo = myBundle?.getStringArrayList("ownerInfo")
        val licenseInfo = myBundle?.getStringArrayList("licenseInfo")

        if(reserveInfo!=null){

            startDate = reserveInfo.elementAt(0)
            endDate = reserveInfo.elementAt(1)
            pickupTime = reserveInfo.elementAt(2)

            val multiplier = getDateInteger(startDate,endDate)

            Handler().postDelayed({
                binding.etFullName.setText(ownerInfo?.elementAt(2))
                binding.etFullAddress.setText(ownerInfo?.elementAt(5))
                binding.etFullContact.setText(ownerInfo?.elementAt(6))
                binding.etDateReserve.setText("$startDate to $endDate")
                binding.etTimeDetail.setText("$pickupTime")

                val cost1 = 3500 * multiplier
                var cost2:Int = 0

                if(reserveInfo.elementAt(3)== "Borrower Self Drive"){
                    cost2 = 0
                }else{
                    cost2 = 500 * multiplier
                }
                val cost3 = cost1 + cost2

                binding.etVehicleRentCost.setText(cost1.toString())
                binding.etAddistionalCost.setText(cost2.toString())
                binding.etTotalCost.setText(cost3.toString())
            },500)
        }

        binding.btnAcceptCost.setOnClickListener {
            //var bookInfo = arrayListOf<String>()
            reserveInfo?.add(binding.etTotalCost.text.toString())

            Log.d("VehicleCostA",reserveInfo.toString())
            setFragmentResult("requestKey", bundleOf("bookInfoKey" to reserveInfo))
            onBookingNow?.invoke(0)
        }

        return binding.root
    }

    private fun getDateInteger(start: String, end: String): Int {
        val date1 = convertDate(start).timeInMillis/DAY1_INMILLIS
        val date2 = convertDate(end).timeInMillis/DAY1_INMILLIS
        return (date2 - date1).toInt()
    }

    private fun convertDate(dateValue:String): Calendar {
        val dateFormat = SimpleDateFormat("MM/dd/yyy")
        val myDate = dateFormat.parse(dateValue) as Date
        var myCalendar = Calendar.getInstance()

        myCalendar.time = myDate
        return myCalendar
    }
}