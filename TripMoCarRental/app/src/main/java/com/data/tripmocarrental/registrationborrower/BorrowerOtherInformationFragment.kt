package com.data.tripmocarrental.registrationborrower

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.FragmentBorrowerOtherInformationBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class BorrowerOtherInformationFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentBorrowerOtherInformationBinding

    //Invoke variable
    var onNextProcess:((Int)->Unit)?=null

    //Date Selection Variable
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MM/dd/yyy", Locale.US)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBorrowerOtherInformationBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        binding.etExpirationDate.setOnClickListener {
            DatePickerDialog(this.requireContext(),this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        //Button action when click Next Process
        binding.apply {
            btnNextProcess.setOnClickListener {
                onNextProcess?.invoke(0)
            }
        }


        return binding.root
    }

    //Function for Date Selection
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year,month,dayOfMonth)
        displayFormattedDate(calendar.timeInMillis)
    }
    private fun displayFormattedDate(timestamp:Long){
        binding.etExpirationDate.setText(formatter.format(timestamp))
    }
    //End Function for Date Selection
}