package com.data.tripmocarrental.borrower.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.common.DisplayActivity
import com.data.tripmocarrental.databinding.VehicleReserveLayoutBinding
import com.data.tripmocarrental.dataclass.ReserveInfo

class ReserveVehicleInfoAdapter3(private val vehicle:List<ReserveInfo>): RecyclerView.Adapter<ReserveVehicleListViewHolder3>() {
    private lateinit var myContext: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReserveVehicleListViewHolder3 {
        myContext = parent.context
        val inflater = LayoutInflater.from(myContext)
        val binding = VehicleReserveLayoutBinding.inflate(inflater,parent, false)

        return ReserveVehicleListViewHolder3(binding)
    }

    override fun getItemCount(): Int {
        return vehicle.size
    }

    override fun onBindViewHolder(holder: ReserveVehicleListViewHolder3, position: Int) {
        holder.searchReserveBinding(vehicle[position])

        holder.itemView.setOnClickListener {
            //Toast.makeText(myContext, "Adapter 2", Toast.LENGTH_SHORT).show()
            val selectedItem = ArrayList<String>()
            selectedItem.add(vehicle[position].borrowerEmail)
            selectedItem.add(vehicle[position].borrowerName)
            selectedItem.add(vehicle[position].borrowerAddress)
            selectedItem.add(vehicle[position].borrowerContact)
            selectedItem.add(vehicle[position].licenseInfo)
            selectedItem.add(vehicle[position].licenseCode)
            selectedItem.add(vehicle[position].ownerEmail)
            selectedItem.add(vehicle[position].ownerName)
            selectedItem.add(vehicle[position].ownerAddress)
            selectedItem.add(vehicle[position].ownerContact)
            selectedItem.add(vehicle[position].vehicleID)
            selectedItem.add(vehicle[position].vehicleName)
            selectedItem.add(vehicle[position].vehicleSpecification)
            selectedItem.add(vehicle[position].vehicleRegistration)
            selectedItem.add(vehicle[position].driveMode)
            selectedItem.add(vehicle[position].reservedStart)
            selectedItem.add(vehicle[position].reserveEnd)
            selectedItem.add(vehicle[position].reservePick)
            selectedItem.add(vehicle[position].reservedCost)
            selectedItem.add(vehicle[position].reserveStatus)



            var nextScreen = Intent(myContext, DisplayActivity::class.java)
            nextScreen.putExtra("selectedItem",selectedItem)
            myContext.startActivity(nextScreen)
        }

    }
}