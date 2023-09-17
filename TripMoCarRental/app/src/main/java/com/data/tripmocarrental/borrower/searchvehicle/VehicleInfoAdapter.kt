package com.data.tripmocarrental.borrower.searchvehicle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.borrower.CarReservationActivity
import com.data.tripmocarrental.databinding.VehicleListLayoutBinding
import com.data.tripmocarrental.dataclass.VehicleInfo

class VehicleInfoAdapter(private val vehicle:List<VehicleInfo>,private val currentUser:ArrayList<String>):RecyclerView.Adapter<VehicleListViewHolder>() {
    var onItemClick:((Int)->Unit)?=null
    //var onItemClick:((ArrayList<VehicleInfo>)->Unit)?=null
    private lateinit var myContext:Context
    private lateinit var userInfo:ArrayList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListViewHolder {
        myContext = parent.context
        val inflater = LayoutInflater.from(myContext)
        val binding = VehicleListLayoutBinding.inflate(inflater,parent,false)
        userInfo = currentUser
        return VehicleListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return vehicle.size
    }

    override fun onBindViewHolder(holder: VehicleListViewHolder, position: Int) {
        holder.searchVehicleBinding(vehicle[position])

        holder.itemView.setOnClickListener {
            //val myArray = vehicle[position] as ArrayList<VehicleInfo>
            //onItemClick?.invoke(position)
            //Log.d("Vehicle1",position.toString())


            val selectedVehicle = ArrayList<String>()
            selectedVehicle.add(vehicle[position].vehicleOwner) //0
            selectedVehicle.add(vehicle[position].vehicleBrand) //1
            selectedVehicle.add(vehicle[position].vehicleModel) //2
            selectedVehicle.add(vehicle[position].vehicleColor) //3
            selectedVehicle.add(vehicle[position].vehicleCapacity.toString()) //4
            selectedVehicle.add(vehicle[position].vehicleCategory) //5
            selectedVehicle.add(vehicle[position].vehicleTransmission) //6
            selectedVehicle.add(vehicle[position].vehiclePlateNo) //7
            selectedVehicle.add(vehicle[position].vehicleCerfRegNo) //8
            selectedVehicle.add(vehicle[position].vehicleRegisterDate) //9
            selectedVehicle.add(vehicle[position].vehicleDriveMode) //10
            selectedVehicle.add(vehicle[position].vehicleRentalCost) //11
            selectedVehicle.add(vehicle[position].vehicleCity) //12
            selectedVehicle.add(vehicle[position].vehicleProvince) //13

            val nextScreen = Intent(myContext,CarReservationActivity::class.java)
            //val adapterBundle = Bundle()
            nextScreen.putExtra("selectedVehicle",selectedVehicle)
            //adapterBundle.putStringArrayList("selectedVehicle",selectedVehicle)
            //adapterBundle.putStringArrayList("userInfo",userInfo)
            //nextScreen.putExtra("userInfo",adapterBundle)
            //nextScreen.putExtra("userInfo",adapterBundle)
            //Log.d("VehicleAdapter",userInfo.toString())
            myContext.startActivity(nextScreen)
        }
    }

    private fun proceedBooking(){

    }
}