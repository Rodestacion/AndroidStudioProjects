package com.data.tripmocarrental.borrower.home

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.databinding.FragmentBorrowerHomeBinding
import com.data.tripmocarrental.dataclass.ReserveInfo
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
        var thisBundle = arguments
        var userInfo = thisBundle?.getStringArrayList("userInfo")

        //initialize recyclerview
        recyclerView = binding.reserveRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        //Handler().postDelayed({
            myVehicleList = getAllVehicleData(userInfo!!.elementAt(0))
        //},1000)

        Handler().postDelayed({
            myReserveVehicleInfoAdapter = ReserveVehicleInfoAdapter(myVehicleList)
            recyclerView.adapter=  myReserveVehicleInfoAdapter
            if(myVehicleList.size>0){
                Toast.makeText(requireContext(), "Check the above list of pending reservation", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "No pending reservation to display", Toast.LENGTH_SHORT).show()
            }

        },1100)



        return binding.root
    }

    private fun getAllVehicleData(email: String): ArrayList<ReserveInfo> {
        var vehicleList = ArrayList<ReserveInfo>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("reserveList")

        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data
                    if(details["borrowerEmail"].toString() == email && details["reserveStatus"].toString()=="For Approval"){
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
                            details["vehicleID"].toString(),
                            details["vehicleName"].toString(),
                            details["vehicleSpecification"].toString(),
                            details["vehicleRegistration"].toString(),
                            details["driveMode"].toString(),
                            details["reservedStart"].toString(),
                            details["reserveEnd"].toString(),
                            details["reservePick"].toString(),
                            details["reservedCost"].toString(),
                            details["reserveStatus"].toString()
                        )
                        vehicleList.add(newReserveList)
                    }

                }
            }
        return vehicleList
    }
}