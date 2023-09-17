package com.data.tripmocarrental.borrower.home

import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.databinding.VehicleReserveLayoutBinding
import com.data.tripmocarrental.dataclass.ReserveInfo

class ReserveVehicleListViewHolder(private val binding:VehicleReserveLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    fun searchReserveBinding(reserveInfo: ReserveInfo){
        binding.txtCarBrandModel.text = reserveInfo.vehicleName
        binding.txtReserveDate.text = reserveInfo.reservedStart
        binding.txtPickupTime.text = reserveInfo.reservePick
        binding.txtLocationDetail.text = ""
        binding.txtApprovalStatus.text = reserveInfo.reserveStatus
    }
}