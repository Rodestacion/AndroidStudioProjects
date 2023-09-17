package com.data.tripmocarrental.borrower.home

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.FragmentBorrowerHomeBinding
import com.data.tripmocarrental.dataclass.ReserveInfo
import com.data.tripmocarrental.dataclass.VehicleInfo
import com.google.firebase.firestore.FirebaseFirestore

class BorrowerHomeFragment : Fragment() {
    private lateinit var binding: FragmentBorrowerHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var myReserveVehicleInfoAdapter: ReserveVehicleInfoAdapter
    var myVehicleList = mutableListOf<ReserveInfo>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBorrowerHomeBinding.inflate(layoutInflater,container,false)

    //initialize recyclerview
        recyclerView = binding.reserveRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        myVehicleList = getAllVehicleData()

        Handler().postDelayed({
            myReserveVehicleInfoAdapter = ReserveVehicleInfoAdapter(myVehicleList)
            recyclerView.adapter=  myReserveVehicleInfoAdapter

        },1000)



        return binding.root
    }

    private fun getAllVehicleData(): ArrayList<ReserveInfo> {
        var vehicleList = ArrayList<ReserveInfo>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("reserveList")

        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data
                    //Log.d("VEHICLE1",details.toString())
                    val newReserveList = ReserveInfo(
                        details["borrowerEmail"].toString(),
                        details["borrowerName"].toString(),
                        details["borrowerAddress"].toString(),
                        details["borrowerContact"].toString(),
                        details["licenseInfo"].toString(),
                        details["licenseCode"].toString(),
                        details["ownerEmail"].toString(),
                        details["ownerName"].toString(),
                        details["ownerAddress"].toString(),
                        details["ownerContact"].toString(),
                        details["vehicleName"].toString(),
                        details["vehicleSpecification"].toString(),
                        details["vehicleRegistration"].toString(),
                        details["reservedStart"].toString(),
                        details["reserveEnd"].toString(),
                        details["reservePick"].toString(),
                        details["reservedCost"].toString(),
                        details["reserveStatus"].toString()
                    )
                    vehicleList.add(newReserveList)
                }
            }
        return vehicleList
    }
}