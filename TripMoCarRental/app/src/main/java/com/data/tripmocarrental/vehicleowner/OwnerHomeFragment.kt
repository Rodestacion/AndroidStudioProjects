package com.data.tripmocarrental.vehicleowner

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.tripmocarrental.R
import com.data.tripmocarrental.borrower.home.ReserveVehicleInfoAdapter
import com.data.tripmocarrental.databinding.FragmentOwnerHomeBinding
import com.data.tripmocarrental.dataclass.ReserveInfo
import com.data.tripmocarrental.dataclass.ReserveInfo2
import com.google.firebase.firestore.FirebaseFirestore


class OwnerHomeFragment : Fragment() {
    private lateinit var binding: FragmentOwnerHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var myReserveVehicleInfoAdapter: ReserveVehicleInfoAdapter2
    var myVehicleList = mutableListOf<ReserveInfo2>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOwnerHomeBinding.inflate(layoutInflater,container,false)

        var thisBundle = arguments
        var userInfo = thisBundle?.getStringArrayList("userInfo")

        //initialize recyclerview
        recyclerView = binding.reserveOwnerRecycleView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        //Handler().postDelayed({
        myVehicleList = getAllVehicleData(userInfo!!.elementAt(0))
        //},1000)

        Handler().postDelayed({
            myReserveVehicleInfoAdapter = ReserveVehicleInfoAdapter2(myVehicleList)
            recyclerView.adapter=  myReserveVehicleInfoAdapter
            if(myVehicleList.size>0){
                Toast.makeText(requireContext(), "Check the above list of pending reservation", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "No pending reservation to display", Toast.LENGTH_SHORT).show()
            }

        },1100)


        return binding.root
    }

    private fun getAllVehicleData(email: String): ArrayList<ReserveInfo2> {
        var vehicleList = ArrayList<ReserveInfo2>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("reserveList")

        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data
                    if(details["ownerEmail"].toString() == email && details["reserveStatus"].toString()=="For Approval"){
                        val newReserveList = ReserveInfo2(
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
                            details["reserveStatus"].toString(),
                            documentSnapshot.id.toString()
                        )
                        vehicleList.add(newReserveList)
                    }

                }
            }
        return vehicleList
    }
}