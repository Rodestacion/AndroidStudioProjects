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
import com.data.tripmocarrental.borrower.searchvehicle.VehicleInfoAdapter
import com.data.tripmocarrental.databinding.FragmentSearchVehicleBinding
import com.data.tripmocarrental.dataclass.VehicleInfo
import com.google.firebase.firestore.FirebaseFirestore


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

        //initialize Data
        var myBundle = arguments
        val userInfo = myBundle?.getStringArrayList("userInfo")

        //initialize recyclerview
        recyclerView = binding.vehicleRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        myVehicleInfoAdapter = VehicleInfoAdapter(myVehicleList, userInfo!!)
        recyclerView.adapter = myVehicleInfoAdapter

        binding.btnSearchCar.setOnClickListener{
            if(binding.etSearchBox.text.toString()==""){
                myVehicleList.clear()
                myVehicleInfoAdapter.notifyDataSetChanged()
                myVehicleList = getAllVehicleData()

                Handler().postDelayed({
                    myVehicleInfoAdapter = VehicleInfoAdapter(myVehicleList,userInfo)
                    recyclerView.adapter = myVehicleInfoAdapter
                    myVehicleInfoAdapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Search Complete!", Toast.LENGTH_SHORT).show()
                    //onResume()
                },1000)
            }else{
                myVehicleList.clear()
                myVehicleInfoAdapter.notifyDataSetChanged()
                val searchItem = binding.etSearchBox.text.toString()
                myVehicleList = getBrandVehicleData(searchItem)

                Handler().postDelayed({
                    if(myVehicleList.size>0){
                        myVehicleInfoAdapter = VehicleInfoAdapter(myVehicleList,userInfo)
                        recyclerView.adapter = myVehicleInfoAdapter
                        myVehicleInfoAdapter.notifyDataSetChanged()
                        Toast.makeText(requireContext(), "Search Complete!", Toast.LENGTH_SHORT).show()
                    }else{
                        myVehicleList = getModelVehicleData(searchItem)

                        Handler().postDelayed({
                            if(myVehicleList.size>0){
                                myVehicleInfoAdapter = VehicleInfoAdapter(myVehicleList,userInfo)
                                recyclerView.adapter = myVehicleInfoAdapter
                                myVehicleInfoAdapter.notifyDataSetChanged()
                                Toast.makeText(requireContext(), "Search Complete!", Toast.LENGTH_SHORT).show()
                            }else{
                                myVehicleList = getCityVehicleData(searchItem)

                                Handler().postDelayed({
                                    if(myVehicleList.size>0){
                                        myVehicleInfoAdapter = VehicleInfoAdapter(myVehicleList,userInfo)
                                        recyclerView.adapter = myVehicleInfoAdapter
                                        myVehicleInfoAdapter.notifyDataSetChanged()
                                        Toast.makeText(requireContext(), "Search Complete!", Toast.LENGTH_SHORT).show()
                                    }else{
                                        myVehicleList = getProvinceVehicleData(searchItem)

                                        Handler().postDelayed({
                                            if(myVehicleList.size>0){
                                                myVehicleInfoAdapter = VehicleInfoAdapter(myVehicleList,userInfo)
                                                recyclerView.adapter = myVehicleInfoAdapter
                                                myVehicleInfoAdapter.notifyDataSetChanged()
                                                Toast.makeText(requireContext(), "Search Complete!", Toast.LENGTH_SHORT).show()
                                            }else{
                                                Toast.makeText(requireContext(), "Result: No data found", Toast.LENGTH_SHORT).show()
                                            }
                                            //onResume()
                                        },1000)
                                    }
                                    //onResume()
                                },1000)
                            }
                            //onResume()
                        },1000)
                    }
                    //onResume()
                },1000)
            }
        }

        myVehicleInfoAdapter.onItemClick = {vehicle->
            //CoroutineScope()
            Log.d("Vehicle3",vehicle.toString())
        }

        return binding.root
    }

    private fun getBrandVehicleData(searchItem: String): ArrayList<VehicleInfo> {
        var vehicleList = ArrayList<VehicleInfo>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("vehicleList")

        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data

                    if(details["vehicleBrand"].toString()==searchItem.uppercase()) {
                        val newVehicleList = VehicleInfo(
                            details["vehicleOwner"].toString(),
                            details["vehicleBrand"].toString(),
                            details["vehicleModel"].toString(),
                            details["vehicleColor"].toString(),
                            details["vehicleCapacity"].toString().toInt(),
                            details["vehicleCategory"].toString(),
                            details["vehicleTransmission"].toString(),
                            details["vehiclePlateNo"].toString(),
                            details["vehicleCerfRegNo"].toString(),
                            details["vehicleRegisterDate"].toString(),
                            details["vehicleDriveMode"].toString(),
                            details["vehicleRentalCost"].toString(),
                            details["vehicleCity"].toString(),
                            details["vehicleProvince"].toString()
                        )
                        vehicleList.add(newVehicleList)
                    }
                }
            }
        return vehicleList
    }

    private fun getModelVehicleData(searchItem: String): ArrayList<VehicleInfo> {
        var vehicleList = ArrayList<VehicleInfo>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("vehicleList")

        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data

                    if(details["vehicleModel"].toString()==searchItem.uppercase()) {
                        val newVehicleList = VehicleInfo(
                            details["vehicleOwner"].toString(),
                            details["vehicleBrand"].toString(),
                            details["vehicleModel"].toString(),
                            details["vehicleColor"].toString(),
                            details["vehicleCapacity"].toString().toInt(),
                            details["vehicleCategory"].toString(),
                            details["vehicleTransmission"].toString(),
                            details["vehiclePlateNo"].toString(),
                            details["vehicleCerfRegNo"].toString(),
                            details["vehicleRegisterDate"].toString(),
                            details["vehicleDriveMode"].toString(),
                            details["vehicleRentalCost"].toString(),
                            details["vehicleCity"].toString(),
                            details["vehicleProvince"].toString()
                        )
                        vehicleList.add(newVehicleList)
                    }
                }
            }
        return vehicleList
    }

    private fun getCityVehicleData(searchItem: String): ArrayList<VehicleInfo> {
        var vehicleList = ArrayList<VehicleInfo>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("vehicleList")

        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data

                    if(details["vehicleCity"].toString()==searchItem.uppercase()) {
                        val newVehicleList = VehicleInfo(
                            details["vehicleOwner"].toString(),
                            details["vehicleBrand"].toString(),
                            details["vehicleModel"].toString(),
                            details["vehicleColor"].toString(),
                            details["vehicleCapacity"].toString().toInt(),
                            details["vehicleCategory"].toString(),
                            details["vehicleTransmission"].toString(),
                            details["vehiclePlateNo"].toString(),
                            details["vehicleCerfRegNo"].toString(),
                            details["vehicleRegisterDate"].toString(),
                            details["vehicleDriveMode"].toString(),
                            details["vehicleRentalCost"].toString(),
                            details["vehicleCity"].toString(),
                            details["vehicleProvince"].toString()
                        )
                        vehicleList.add(newVehicleList)
                    }
                }
            }
        return vehicleList
    }

    private fun getProvinceVehicleData(searchItem: String): ArrayList<VehicleInfo> {
        var vehicleList = ArrayList<VehicleInfo>()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("vehicleList")

        collectionRef.get()
            .addOnSuccessListener { QuerySnapshot->
                for (documentSnapshot in QuerySnapshot){
                    val details = documentSnapshot.data

                    if(details["vehicleProvince"].toString()==searchItem.uppercase()) {
                        val newVehicleList = VehicleInfo(
                            details["vehicleOwner"].toString(),
                            details["vehicleBrand"].toString(),
                            details["vehicleModel"].toString(),
                            details["vehicleColor"].toString(),
                            details["vehicleCapacity"].toString().toInt(),
                            details["vehicleCategory"].toString(),
                            details["vehicleTransmission"].toString(),
                            details["vehiclePlateNo"].toString(),
                            details["vehicleCerfRegNo"].toString(),
                            details["vehicleRegisterDate"].toString(),
                            details["vehicleDriveMode"].toString(),
                            details["vehicleRentalCost"].toString(),
                            details["vehicleCity"].toString(),
                            details["vehicleProvince"].toString()
                        )
                        vehicleList.add(newVehicleList)
                    }
                }
            }
        return vehicleList
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
                        details["vehicleColor"].toString(),
                        details["vehicleCapacity"].toString().toInt(),
                        details["vehicleCategory"].toString(),
                        details["vehicleTransmission"].toString(),
                        details["vehiclePlateNo"].toString(),
                        details["vehicleCerfRegNo"].toString(),
                        details["vehicleRegisterDate"].toString(),
                        details["vehicleDriveMode"].toString(),
                        details["vehicleRentalCost"].toString(),
                        details["vehicleCity"].toString(),
                        details["vehicleProvince"].toString()
                    )
                    vehicleList.add(newVehicleList)
                }
            }
        return vehicleList
    }

}