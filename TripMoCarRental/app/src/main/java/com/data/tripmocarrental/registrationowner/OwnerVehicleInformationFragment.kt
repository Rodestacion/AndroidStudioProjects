package com.data.tripmocarrental.registrationowner

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
import com.data.tripmocarrental.databinding.FragmentOwnerVehicleInformationBinding
import com.data.tripmocarrental.databinding.VehicleCodeLayoutBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class OwnerVehicleInformationFragment : Fragment(),DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentOwnerVehicleInformationBinding

    //Invoke variable
    var onNextProcess:((Int)->Unit)?=null

    //Date Selection Variable
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MM/dd/yyy", Locale.US)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerVehicleInformationBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment


        binding.etRegisterDate.setOnClickListener {
            DatePickerDialog(this.requireContext(),this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.etVehicleCategory.setOnClickListener {
            selectVehicleCode()
        }

        //Button action when click Next Process
        binding.apply {
            btnNextProcess.setOnClickListener {
                onNextProcess?.invoke(0)
            }
        }

        return binding.root
    }

    //Vehicle Category Dialog box
    private fun selectVehicleCode() {
        val alertDialogBuilder = AlertDialog.Builder(this.requireContext())
        alertDialogBuilder.setTitle("Vehicle Code Selection")

        val dialogLayout = layoutInflater.inflate(R.layout.vehicle_code_layout,null)
        val dialogBinding = VehicleCodeLayoutBinding.bind(dialogLayout)

        var vehicleCode :String = ""
        dialogBinding.radGrpVehicleCode.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radBtnL1->{
                    vehicleCode = dialogBinding.radBtnL1.text.toString()
                }
                R.id.radBtnL2->{
                    vehicleCode = dialogBinding.radBtnL2.text.toString()
                }
                R.id.radBtnL3->{
                    vehicleCode = dialogBinding.radBtnL3.text.toString()
                }
                R.id.radBtnL4->{
                    vehicleCode = dialogBinding.radBtnL4.text.toString()
                }
                R.id.radBtnL5->{
                    vehicleCode = dialogBinding.radBtnL5.text.toString()
                }
                R.id.radBtnL6->{
                    vehicleCode = dialogBinding.radBtnL6.text.toString()
                }
                R.id.radBtnL7->{
                    vehicleCode = dialogBinding.radBtnL7.text.toString()
                }
                R.id.radBtnM1->{
                    vehicleCode = dialogBinding.radBtnM1.text.toString()
                }
                R.id.radBtnM2->{
                    vehicleCode = dialogBinding.radBtnM2.text.toString()
                }
                R.id.radBtnM3->{
                    vehicleCode = dialogBinding.radBtnM3.text.toString()
                }
                R.id.radBtnN1->{
                    vehicleCode = dialogBinding.radBtnN1.text.toString()
                }
                R.id.radBtnN2->{
                    vehicleCode = dialogBinding.radBtnN2.text.toString()
                }
                R.id.radBtnN3->{
                    vehicleCode = dialogBinding.radBtnN3.text.toString()
                }
                R.id.radBtnO1->{
                    vehicleCode = dialogBinding.radBtnO1.text.toString()
                }
                R.id.radBtnO2->{
                    vehicleCode = dialogBinding.radBtnO2.text.toString()
                }
            }
        }

        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK"){dialog,_->

            binding.etVehicleCategory.setText(vehicleCode)

            dialog.dismiss()
        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    //Function for Date Selection
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year,month,dayOfMonth)
        displayFormattedDate(calendar.timeInMillis)
    }
    private fun displayFormattedDate(timestamp:Long){
        binding.etRegisterDate.setText(formatter.format(timestamp))
    }
    //End Function for Date Selection
}