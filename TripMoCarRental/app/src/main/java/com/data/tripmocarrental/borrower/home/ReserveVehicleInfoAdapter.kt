package com.data.tripmocarrental.borrower.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.databinding.VehicleReserveLayoutBinding
import com.data.tripmocarrental.dataclass.ReserveInfo

class ReserveVehicleInfoAdapter(private val vehicle:List<ReserveInfo>):RecyclerView.Adapter<ReserveVehicleListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReserveVehicleListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VehicleReserveLayoutBinding.inflate(inflater,parent, false)

        return ReserveVehicleListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return vehicle.size
    }

    override fun onBindViewHolder(holder: ReserveVehicleListViewHolder, position: Int) {
        holder.searchReserveBinding(vehicle[position])
    }
}