package com.data.tripmocarrental.borrower.searchvehicle

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.borrower.CarReservationActivity
import com.data.tripmocarrental.databinding.VehicleListLayoutBinding
import com.data.tripmocarrental.dataclass.VehicleInfo

class VehicleInfoAdapter(private val vehicle:List<VehicleInfo>):RecyclerView.Adapter<VehicleListViewHolder>() {
    var onItemClick:((VehicleInfo)->Unit)?=null
    private lateinit var myContext:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListViewHolder {
        myContext = parent.context
        val inflater = LayoutInflater.from(myContext)
        val binding = VehicleListLayoutBinding.inflate(inflater,parent,false)
        return VehicleListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return vehicle.size
    }

    override fun onBindViewHolder(holder: VehicleListViewHolder, position: Int) {
        holder.searchVehicleBinding(vehicle[position])

        holder.itemView.setOnClickListener {
//            onItemClick?.invoke(vehicle[position])
//            Log.d("Vehicle1",vehicle[position].toString())
            val nextScreen = Intent(myContext,CarReservationActivity::class.java)
            myContext.startActivity(nextScreen)
        }
    }
}