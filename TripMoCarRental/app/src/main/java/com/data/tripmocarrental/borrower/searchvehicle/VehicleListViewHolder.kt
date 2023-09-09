package com.data.tripmocarrental.borrower.searchvehicle

import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.databinding.VehicleListLayoutBinding
import com.data.tripmocarrental.dataclass.VehicleInfo

class VehicleListViewHolder(private val binding: VehicleListLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    fun searchVehicleBinding(vehicleInfo:VehicleInfo){
        binding.txtCarBrandModel.text = "${vehicleInfo.vehicleBrand} / ${vehicleInfo.vehicleModel}"

        if(vehicleInfo.vehicleDriveMode=="Allowed Both"){
            binding.txtDrivingMode.text = "Drive by Owner/Self Drive"
        }else{
            binding.txtDrivingMode.text = vehicleInfo.vehicleDriveMode
        }

        binding.txtSeatingCpacity.text = vehicleInfo.vehicleCapacity.toString()
        binding.txtTransmissionType.text =vehicleInfo.vehicleTransmission
        binding.txtLocation.text = ""
    }
}