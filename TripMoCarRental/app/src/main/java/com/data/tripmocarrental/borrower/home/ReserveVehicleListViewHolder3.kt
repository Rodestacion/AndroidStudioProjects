package com.data.tripmocarrental.borrower.home

import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.databinding.VehicleReserveLayoutBinding
import com.data.tripmocarrental.dataclass.ReserveInfo

class ReserveVehicleListViewHolder3(private val binding: VehicleReserveLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    fun searchReserveBinding(reserveInfo: ReserveInfo){
        binding.txtCarBrandModel.text = reserveInfo.vehicleName
        binding.txtReserveDate.text = "${reserveInfo.reservedStart} to ${reserveInfo.reserveEnd}"
        binding.txtPickupTime.text = reserveInfo.reservePick
        binding.txtLocationDetail.text = reserveInfo.borrowerAddress
        binding.txtApprovalStatus.text = reserveInfo.reserveStatus
    }
}