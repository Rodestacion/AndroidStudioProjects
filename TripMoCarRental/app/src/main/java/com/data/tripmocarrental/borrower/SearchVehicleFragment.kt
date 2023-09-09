package com.data.tripmocarrental.borrower


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.R
import com.data.tripmocarrental.borrower.searchvehicle.VehicleInfoAdapter
import com.data.tripmocarrental.databinding.FragmentSearchVehicleBinding
import com.data.tripmocarrental.dataclass.VehicleInfo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class SearchVehicleFragment : Fragment() {
    private lateinit var binding: FragmentSearchVehicleBinding
    private lateinit var recyclerView:RecyclerView
    private lateinit var myVehicleInfoAdapter: VehicleInfoAdapter
    var myVehicleList = mutableListOf<VehicleInfo>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchVehicleBinding.inflate(layoutInflater, container, false)

        //initialize recyclerview
        recyclerView = binding.vehicleRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        myVehicleInfoAdapter = VehicleInfoAdapter(myVehicleList)
        recyclerView.adapter = myVehicleInfoAdapter

        binding.btnSearchCar.setOnClickListener{
            myVehicleList.clear()
            myVehicleInfoAdapter.notifyDataSetChanged()
            myVehicleList = getAllVehicleData()

            Handler().postDelayed({
                myVehicleInfoAdapter = VehicleInfoAdapter(myVehicleList)
                recyclerView.adapter = myVehicleInfoAdapter
                myVehicleInfoAdapter.notifyDataSetChanged()
                onResume()
            },1000)

        }

        myVehicleInfoAdapter.onItemClick = {vehicle->
            Log.d("Vehicle3",vehicle.toString())
        }

        return binding.root
    }

    private fun getAllVehicleData(): ArrayList<VehicleInfo> {
        var vehicleList = ArrayList<VehicleInfo>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("vehicleList")

        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data
                    //Log.d("VEHICLE1",details.toString())
                    val newVehicleList = VehicleInfo(
                        details["vehicleOwner"].toString(),
                        details["vehicleBrand"].toString(),
                        details["vehicleModel"].toString(),
                        details["vehicleCapacity"].toString().toInt(),
                        details["vehicleCategory"].toString(),
                        details["vehicleTransmission"].toString(),
                        details["vehiclePlateNo"].toString(),
                        details["vehicleCerfRegNo"].toString(),
                        details["vehicleRegisterDate"].toString(),
                        details["vehicleDriveMode"].toString()
                    )
                    vehicleList.add(newVehicleList)
                }
            }
        return vehicleList
    }

}