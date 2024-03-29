package com.data.tripmocarrental.registrationowner

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isEmpty
import androidx.fragment.app.setFragmentResult
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.ConditionCodeLayoutBinding
import com.data.tripmocarrental.databinding.FragmentOwnerVehicleInformationBinding
import com.data.tripmocarrental.databinding.VehicleCodeLayoutBinding
import com.data.tripmocarrental.databinding.VehiclePriceLayoutBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class OwnerVehicleInformationFragment : Fragment(),DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentOwnerVehicleInformationBinding

    //Invoke variable
    var onNextProcess:((Int)->Unit)?=null

    //Date Selection Variable
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MM/dd/yyy", Locale.TAIWAN)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerVehicleInformationBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        binding.etRentalCost.setOnClickListener {
            rentalCost()
        }


        binding.etRegisterDate.setOnClickListener {
            var newCalendar = Calendar.getInstance()
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

        binding.etVehicleCategory.setOnClickListener {
            selectVehicleCode()
        }

        //Button action when click Next Process
        binding.apply {
            btnNextProcess.setOnClickListener {
                if(
                    binding.etBrand.text!!.isEmpty() ||
                    binding.etModel.text!!.isEmpty() ||
                    binding.etColor.text!!.isEmpty() ||
                    binding.etSeatingCapacity.text!!.isEmpty() ||
                    binding.etVehicleCategory.text!!.isEmpty() ||
                    !(binding.radManual.isChecked || binding.radAutomatic.isChecked || binding.radSemiAuto.isChecked) ||
                    binding.etPlateNumber.text!!.isEmpty() ||
                    binding.etCertificateRegistration.text!!.isEmpty() ||
                    binding.etRegisterDate.text!!.isEmpty() ||
                    !(binding.btnRadOwnerDrive.isChecked || binding.btnRadSelfDrive.isChecked || binding.btnRadBothAllowed.isChecked) ||
                    binding.etRentalCost.text!!.isEmpty()
                ){
                    Toast.makeText(requireActivity(), "Filled up the empty field with necessary information", Toast.LENGTH_SHORT).show()
                }else{
                    val vehicleInfo = arrayListOf<String>()

                    vehicleInfo.add(binding.etBrand.text.toString())
                    vehicleInfo.add(binding.etModel.text.toString())
                    vehicleInfo.add(binding.etColor.text.toString())
                    vehicleInfo.add(binding.etSeatingCapacity.text.toString())
                    vehicleInfo.add(binding.etVehicleCategory.text.toString())

                    val transmission = if(binding.radManual.isChecked){
                        binding.radManual.text.toString()
                    }else if(binding.radAutomatic.isChecked){
                        binding.radAutomatic.text.toString()
                    }else{
                        binding.radSemiAuto.text.toString()
                    }

                    vehicleInfo.add(transmission)
                    vehicleInfo.add(binding.etPlateNumber.text.toString())
                    vehicleInfo.add(binding.etCertificateRegistration.text.toString())
                    vehicleInfo.add(binding.etRegisterDate.text.toString())

                    val drivingMode = if(binding.btnRadOwnerDrive.isChecked){
                        binding.btnRadOwnerDrive.text.toString()
                    }else if(binding.btnRadSelfDrive.isChecked){
                        binding.btnRadSelfDrive.text.toString()
                    }else{
                        binding.btnRadBothAllowed.text.toString()
                    }
                    vehicleInfo.add(drivingMode)
                    vehicleInfo.add(binding.etRentalCost.text.toString())

                    setFragmentResult("requestKey", bundleOf("vehicleInfoKey" to vehicleInfo))
                    onNextProcess?.invoke(2)
                }

                //For Checking only
                //onNextProcess?.invoke(0)
            }
        }

        return binding.root
    }

    //RentalCost
    private fun rentalCost(){
        val alertDialogBuilder = AlertDialog.Builder(this.requireContext())
        alertDialogBuilder.setTitle("Vehicle Rental Cost")
        val dialogLayout = layoutInflater.inflate(R.layout.vehicle_price_layout,null)
        val dialogBinding = VehiclePriceLayoutBinding.bind(dialogLayout)
        var rentCost:String = ""
        dialogBinding.radRentalCost.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radV1Price->{
                    rentCost = dialogBinding.radV1Price.text.toString()
                }
                R.id.radV2Price->{
                    rentCost = dialogBinding.radV2Price.text.toString()
                }
                R.id.radV3Price->{
                    rentCost = dialogBinding.radV3Price.text.toString()
                }
                R.id.radV4Price->{
                    rentCost = dialogBinding.radV4Price.text.toString()
                }
                R.id.radV5Price->{
                    rentCost = dialogBinding.radV5Price.text.toString()
                }
                R.id.radV6Price->{
                    rentCost = dialogBinding.radV6Price.text.toString()
                }
                R.id.radV7Price->{
                    rentCost = dialogBinding.radV7Price.text.toString()
                }
                R.id.radV8Price->{
                    rentCost = dialogBinding.radV8Price.text.toString()
                }
            }
        }
        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK"){dialog,_->
            binding.etRentalCost.setText(rentCost)
            dialog.dismiss()
        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
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