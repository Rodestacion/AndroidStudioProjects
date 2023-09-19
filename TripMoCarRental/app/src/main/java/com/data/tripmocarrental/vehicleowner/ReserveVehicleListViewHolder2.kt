package com.data.tripmocarrental.vehicleowner

import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.databinding.VehicleReserveLayoutBinding
import com.data.tripmocarrental.dataclass.ReserveInfo2

class ReserveVehicleListViewHolder2 (private val binding: VehicleReserveLayoutBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun searchReserveBinding(reserveInfo: ReserveInfo2){
        binding.txtCarBrandModel.text = reserveInfo.vehicleName
        binding.txtReserveDate.text = "${reserveInfo.reservedStart} to ${reserveInfo.reserveEnd}"
        binding.txtPickupTime.text = reserveInfo.reservePick
        binding.txtLocationDetail.text = reserveInfo.ownerAddress
        binding.txtApprovalStatus.text = reserveInfo.reserveStatus
    }
}