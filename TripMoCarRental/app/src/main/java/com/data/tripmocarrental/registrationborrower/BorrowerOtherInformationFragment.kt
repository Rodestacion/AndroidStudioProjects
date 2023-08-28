package com.data.tripmocarrental.registrationborrower

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.ConditionCodeLayoutBinding
import com.data.tripmocarrental.databinding.FragmentBorrowerOtherInformationBinding
import com.data.tripmocarrental.databinding.RestrictionCodeLayoutBinding
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
    ): View {
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

        //Radiobutton group change visibility
        binding.drivingStatus.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.btnRadNoLicense->{
                    binding.licenseDetail.visibility = View.INVISIBLE
                }
                R.id.btnRadWithLicense->{
                    binding.licenseDetail.visibility = View.VISIBLE
                }
            }
        }

        //Restriction Code Selection
        binding.etRestriction.setOnClickListener {
            selectRestrictionCode()
        }

        //Condition Code Selection
        binding.etCondition.setOnClickListener {
            selectConditionCode()
        }


        return binding.root
    }

    //Dialog for Restriction Code
    private fun selectRestrictionCode() {
        val alertDialogBuilder = AlertDialog.Builder(this.requireContext())
        alertDialogBuilder.setTitle("Restriction Code Selection")

        val dialogLayout = layoutInflater.inflate(R.layout.restriction_code_layout,null)
        val dialogBinding = RestrictionCodeLayoutBinding.bind(dialogLayout)

        //initialize
        dialogBinding.btnRadNewCode.isChecked = true
        dialogBinding.cb1.text = "A"
        dialogBinding.cb2.text = "A1"
        dialogBinding.cb3.text = "B"
        dialogBinding.cb4.text = "B1"
        dialogBinding.cb5.text = "B2"
        dialogBinding.cb6.text = "C"
        dialogBinding.cb7.text = "D"
        dialogBinding.cb8.text = "BE"

        //Change Code Selection
        dialogBinding.codeRadGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.btnRadOldCode->{
                    dialogBinding.cb1.text = "1"
                    dialogBinding.cb2.text = "2"
                    dialogBinding.cb3.text = "3"
                    dialogBinding.cb4.text = "4"
                    dialogBinding.cb5.text = "5"
                    dialogBinding.cb6.text = "6"
                    dialogBinding.cb7.text = "7"
                    dialogBinding.cb8.text = "8"
                }
                R.id.btnRadNewCode->{
                    dialogBinding.cb1.text = "A"
                    dialogBinding.cb2.text = "A1"
                    dialogBinding.cb3.text = "B"
                    dialogBinding.cb4.text = "B1"
                    dialogBinding.cb5.text = "B2"
                    dialogBinding.cb6.text = "C"
                    dialogBinding.cb7.text = "D"
                    dialogBinding.cb8.text = "BE"
                }
            }
        }

        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK"){dialog,_->
            var restriction:String =""

            if(dialogBinding.cb1.isChecked){
                restriction = dialogBinding.cb1.text.toString()
            }

            if(dialogBinding.cb2.isChecked){
                if (restriction.isNotEmpty()){
                    restriction = restriction + ";" + dialogBinding.cb2.text.toString()
                }else{
                    restriction = dialogBinding.cb2.text.toString()
                }
            }

            if(dialogBinding.cb3.isChecked){
                if (restriction.isNotEmpty()){
                    restriction = restriction + ";" + dialogBinding.cb3.text.toString()
                }else{
                    restriction = dialogBinding.cb3.text.toString()
                }
            }

            if(dialogBinding.cb4.isChecked){
                if (restriction.isNotEmpty()){
                    restriction = restriction + ";" + dialogBinding.cb4.text.toString()
                }else{
                    restriction = dialogBinding.cb4.text.toString()
                }
            }

            if(dialogBinding.cb5.isChecked){
                if (restriction.isNotEmpty()){
                    restriction = restriction + ";" + dialogBinding.cb5.text.toString()
                }else{
                    restriction = dialogBinding.cb5.text.toString()
                }
            }

            if(dialogBinding.cb6.isChecked){
                if (restriction.isNotEmpty()){
                    restriction = restriction + ";" + dialogBinding.cb6.text.toString()
                }else{
                    restriction = dialogBinding.cb6.text.toString()
                }
            }

            if(dialogBinding.cb7.isChecked){
                if (restriction.isNotEmpty()){
                    restriction = restriction + ";" + dialogBinding.cb7.text.toString()
                }else{
                    restriction = dialogBinding.cb7.text.toString()
                }
            }

            if(dialogBinding.cb8.isChecked){
                if (restriction.isNotEmpty()){
                    restriction = restriction + ";" + dialogBinding.cb8.text.toString()
                }else{
                    restriction = dialogBinding.cb8.text.toString()
                }
            }

            binding.etRestriction.setText(restriction)

            dialog.dismiss()
        }

        val alertDialog:AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    //Dialog for Restriction Code
    private fun selectConditionCode() {
        val alertDialogBuilder = AlertDialog.Builder(this.requireContext())
        alertDialogBuilder.setTitle("Condition Code Selection")

        val dialogLayout = layoutInflater.inflate(R.layout.condition_code_layout,null)
        val dialogBinding = ConditionCodeLayoutBinding.bind(dialogLayout)

        //initialize
        dialogBinding.btnRadNewCode.isChecked = true
        dialogBinding.cb1.text = "1"
        dialogBinding.cb2.text = "2"
        dialogBinding.cb3.text = "3"
        dialogBinding.cb4.text = "4"
        dialogBinding.cb5.text = "5"

        //Change Code Selection
        dialogBinding.codeRadGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.btnRadOldCode->{
                    dialogBinding.cb1.text = "A"
                    dialogBinding.cb2.text = "B"
                    dialogBinding.cb3.text = "C"
                    dialogBinding.cb4.text = "D"
                    dialogBinding.cb5.text = "E"
                }
                R.id.btnRadNewCode->{
                    dialogBinding.cb1.text = "1"
                    dialogBinding.cb2.text = "2"
                    dialogBinding.cb3.text = "3"
                    dialogBinding.cb4.text = "4"
                    dialogBinding.cb5.text = "5"
                }
            }
        }

        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK"){dialog,_->
            var condition:String =""

            if(dialogBinding.cb1.isChecked){
                condition = dialogBinding.cb1.text.toString()
            }

            if(dialogBinding.cb2.isChecked){
                if (condition.isNotEmpty()){
                    condition = condition + ";" + dialogBinding.cb2.text.toString()
                }else{
                    condition = dialogBinding.cb2.text.toString()
                }
            }

            if(dialogBinding.cb3.isChecked){
                if (condition.isNotEmpty()){
                    condition = condition + ";" + dialogBinding.cb3.text.toString()
                }else{
                    condition = dialogBinding.cb3.text.toString()
                }
            }

            if(dialogBinding.cb4.isChecked){
                if (condition.isNotEmpty()){
                    condition = condition + ";" + dialogBinding.cb4.text.toString()
                }else{
                    condition = dialogBinding.cb4.text.toString()
                }
            }

            if(dialogBinding.cb5.isChecked){
                if (condition.isNotEmpty()){
                    condition = condition + ";" + dialogBinding.cb5.text.toString()
                }else{
                    condition = dialogBinding.cb5.text.toString()
                }
            }

            binding.etCondition.setText(condition)
            dialog.dismiss()
        }

        val alertDialog:AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
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