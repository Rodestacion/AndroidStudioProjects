package com.data.tripmocarrental.common

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.data.tripmocarrental.databinding.FragmentProfileBinding
import com.data.tripmocarrental.registrationborrower.BorrowerRegistration
import com.google.type.TimeOfDay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ProfileFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentProfileBinding

    //Invoke variable
    var onNextProcess:((Int)->Unit)?=null

    //Date Selection Variable
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MM/dd/yyy", Locale.TAIWAN)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        binding.etBirthday.setOnClickListener {
            val newCalendar = Calendar.getInstance()

            var dialog = DatePickerDialog(
                this.requireContext(),
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                //.show()
            dialog.getDatePicker().maxDate = newCalendar.timeInMillis
            dialog.show()

        }

        //Button action when click Next Process
        binding.apply {
            btnNextProcess.setOnClickListener {
                if (
                    binding.etFirstName.text!!.isEmpty() ||
                    binding.etMiddleName.text!!.isEmpty() ||
                    binding.etLastName.text!!.isEmpty() ||
                    binding.etAge.text!!.isEmpty() ||
                    binding.etBirthday.text!!.isEmpty()
                ){
                    Toast.makeText(requireActivity(), "Filled up the empty field with necessary information", Toast.LENGTH_SHORT).show()
                }else{
                    var basicInfo = arrayListOf<String>()
                    basicInfo.add(binding.etFirstName.text.toString())
                    basicInfo.add(binding.etMiddleName.text.toString())
                    basicInfo.add(binding.etLastName.text.toString())
                    basicInfo.add(binding.etAge.text.toString())
                    basicInfo.add(binding.etBirthday.text.toString())

                setFragmentResult("requestKey", bundleOf("basicInfoKey" to basicInfo))
                    onNextProcess?.invoke(0)
                }
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
        binding.etBirthday.setText(formatter.format(timestamp))
    }
    //End Function for Date Selection
}