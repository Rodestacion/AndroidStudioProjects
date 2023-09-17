package com.data.tripmocarrental.borrower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.data.tripmocarrental.R
import com.data.tripmocarrental.borrower.reservationfragment.CarCostFragment
import com.data.tripmocarrental.borrower.reservationfragment.CarInfoFragment
import com.data.tripmocarrental.borrower.reservationfragment.CarReservationFragment
import com.data.tripmocarrental.databinding.ActivityCarReservationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class CarReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarReservationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var f1: CarInfoFragment
    private lateinit var f2: CarCostFragment
    private lateinit var f3: CarReservationFragment
    //private lateinit var f4: Fragment
    private lateinit var userInfo:ArrayList<String>
    private lateinit var ownerInfo:ArrayList<String>
    private lateinit var licenseInfo:ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        f1 = CarInfoFragment()
        f2 = CarCostFragment()
        f3 = CarReservationFragment()

        //Adapter Pass Value
        var selectedVehicle = intent.getStringArrayListExtra("selectedVehicle")
        Log.d("VehicleKo",selectedVehicle.toString())

        // Initialize Firebase & user details
        auth = Firebase.auth
        userInfo = getUserDetails()

        if (selectedVehicle != null) {
            ownerInfo = getOwnerDetails(selectedVehicle.elementAt(0))
        }

        Handler().postDelayed({
            if(userInfo.elementAt(7).toBoolean()){
                //licenseInfo.clear()
                Log.d("VehicleLicense",userInfo.elementAt(8))
                licenseInfo = getLicenseDetail(userInfo.elementAt(8))
            }else{
                //licenseInfo.clear()
                val nonLicense = ArrayList<String>()
                nonLicense.add("None")
                licenseInfo = nonLicense
            }
        },1000)

        Handler().postDelayed({
            //Log.d("VehicleReserve",userInfo.toString())
            //Log.d("VehicleReserve",ownerInfo.toString())
            supportFragmentManager.beginTransaction().apply{
                var myBundle = Bundle()
                myBundle.putStringArrayList("selectedVehicle",selectedVehicle)
                myBundle.putStringArrayList("userInfo",userInfo)
                myBundle.putStringArrayList("ownerInfo",ownerInfo)
                myBundle.putStringArrayList("licenseInfo",licenseInfo)
                f1.arguments = myBundle

                replace(R.id.reserveFragmentContainerView,f1)
                commit()
            }
        },2000)

        f1.onBookingCost = {
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val reserveInfo = bundle.getStringArrayList("bookInfoKey")
                Log.d("VehicleCostB",reserveInfo.toString())
                if (reserveInfo!= null){
                    var myBundle = Bundle()
                    myBundle.putStringArrayList("bookInfoKey",reserveInfo)
                    myBundle.putStringArrayList("selectedVehicle",selectedVehicle)
                    myBundle.putStringArrayList("userInfo",userInfo)
                    myBundle.putStringArrayList("ownerInfo",ownerInfo)
                    myBundle.putStringArrayList("licenseInfo",licenseInfo)
                    Log.d("VehicleCostC",myBundle.toString())
                    f2.arguments = myBundle
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.reserveFragmentContainerView,f2)
                        commit()
                    }
                }
            }
        }

        f2.onBookingNow = {
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val reserveInfo = bundle.getStringArrayList("bookInfoKey")
                Log.d("VehicleCostB",reserveInfo.toString())
                if (reserveInfo!= null){
                    var myBundle = Bundle()
                    myBundle.putStringArrayList("bookInfoKey",reserveInfo)
                    myBundle.putStringArrayList("selectedVehicle",selectedVehicle)
                    myBundle.putStringArrayList("userInfo",userInfo)
                    myBundle.putStringArrayList("ownerInfo",ownerInfo)
                    myBundle.putStringArrayList("licenseInfo",licenseInfo)
                    Log.d("VehicleCostC",myBundle.toString())
                    f3.arguments = myBundle

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.reserveFragmentContainerView,f3)
                        commit()
                    }
                }
            }
        }

        f3.onClose = {
            supportFragmentManager.setFragmentResultListener("requestKey",this) { _, bundle ->
                val reserveInfo = bundle.getStringArrayList("bookInfoKey")
                Log.d("VehicleCostB", reserveInfo.toString())
                Log.d("VehicleCostB", reserveInfo?.size.toString())
                addReservationDetail(reserveInfo)

            }
        }

    }

    private fun addReservationDetail(reserveInfo: java.util.ArrayList<String>?){
        //val myID = userInfo.elementAt(4)

        val borrowerEmail = reserveInfo!!.elementAt(0)
        val borrowerName = reserveInfo.elementAt(1)
        val borrowerAddress = reserveInfo.elementAt(2)
        val borrowerContact = reserveInfo.elementAt(3)
        val licenseInfo = reserveInfo.elementAt(4)
        val licenseCode = reserveInfo.elementAt(5)
        val ownerEmail = reserveInfo.elementAt(6)
        val ownerName = reserveInfo.elementAt(7)
        val ownerAddress = reserveInfo.elementAt(8)
        val ownerContact = reserveInfo.elementAt(9)
        val vehicleName = reserveInfo.elementAt(10)
        val vehicleSpecification = reserveInfo.elementAt(11)
        val vehicleRegistration = reserveInfo.elementAt(12)
        val reservedStart = reserveInfo.elementAt(13)
        val reserveEnd = reserveInfo.elementAt(14)
        val reservePick = reserveInfo.elementAt(15)
        val reservedCost = reserveInfo.elementAt(16)
        val reserveStatus = "For Approval"

        var db = FirebaseFirestore.getInstance()
        //val userTypeRef = db.collection("usertype")
        //val documentRef = userTypeRef.document(myID)

        val reserve = hashMapOf<String, Any?>(
            "borrowerEmail" to borrowerEmail,
            "borrowerName" to borrowerName,
            "borrowerAddress" to borrowerAddress,
            "borrowerContact" to borrowerContact,
            "licenseInfo" to licenseInfo,
            "licenseCode" to licenseCode,
            "ownerEmail" to ownerEmail,
            "ownerName" to ownerName,
            "ownerAddress" to ownerAddress,
            "ownerContact" to ownerContact,
            "vehicleName" to vehicleName,
            "vehicleSpecification" to vehicleSpecification,
            "vehicleRegistration" to vehicleRegistration,
            "reservedStart" to reservedStart,
            "reserveEnd" to reserveEnd,
            "reservePick" to reservePick,
            "reservedCost" to reservedCost,
            "reserveStatus" to reserveStatus
        )

        db.collection("reserveList").add(reserve)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Booking Successful", Toast.LENGTH_SHORT).show()
                finish()
//                db = FirebaseFirestore.getInstance()
//                val userTypeRef = db.collection("usertype")
//                val documentRef = userTypeRef.document(myID)
//
//                val update = hashMapOf<String, Any?>(
//                    "licenseInfo" to licenseInfo,
//                    "profileStatus" to "fourth"
//                )
//
//                documentRef.update(update)
//                    .addOnSuccessListener {
//                        Toast.makeText(applicationContext,"License Info Update Successful",Toast.LENGTH_SHORT).show()
//                        supportFragmentManager.beginTransaction().apply {
//                            binding.progressBar3.progress = 60
//                            replace(R.id.borrowerFragmentContainerView,f4)
//                            commit()
//                        }
//                    }
//                    .addOnFailureListener {
//                        Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
//                    }

            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getUserDetails(): ArrayList<String> {
        var userArray = arrayListOf<String>()
        val currentUser = auth.currentUser?.email
        Log.d("Vehicle",currentUser.toString())

        if (currentUser!= null) {
            var collectionName = "usertype"
            var email = currentUser.toString()

            val db = FirebaseFirestore.getInstance()
            val userTypeRef = db.collection(collectionName)

            userTypeRef.whereEqualTo("email", email)
                .get()
                .addOnSuccessListener { QuerySnapshot ->
                    for (document in QuerySnapshot) {
                        val data = document.data
                        val getEmail = data["email"]
                        val getUserType = data["userType"]
                        val getFirstName = data["firstName"]
                        val getMiddleName = data["middleName"]
                        val getLastName = data["lastName"]
                        val getAge = data["age"]
                        val getHouseNo = data["houseNo"]
                        val getStreet = data["street"]
                        val getBuildingSubdivision = data["buildingSubdivision"]
                        val getBarangay = data["barangay"]
                        val getCity = data["city"]
                        val getProvince = data["province"]
                        val getZipCode = data["zipCode"]
                        val getCellphoneNo = data["cellphoneNo"]
                        val getTelephoneNo = data["telephoneNo"]
                        val getLicenseInfo = data["licenseInfo"]
                        val getID = document.id

                        //pass user info as array

                        userArray.add(getEmail.toString())  // index = 0
                        userArray.add(getUserType.toString())  // index = 1
                        val fullName = "$getFirstName $getMiddleName $getLastName"
                        userArray.add(fullName)  // index = 2
                        userArray.add(getAge.toString())  // index = 3
                        val location = "$getCity, $getProvince ($getZipCode)"
                        userArray.add(location)  // index = 4
                        val fullAddress = "$getHouseNo, $getStreet, $getBuildingSubdivision, $getBarangay, $getCity, $getProvince ($getZipCode)"
                        userArray.add(fullAddress)  // index = 5
                        val contactNumber = "$getCellphoneNo / $getTelephoneNo"
                        userArray.add(contactNumber)  // index = 6
                        userArray.add(getLicenseInfo.toString())  // index = 7
                        userArray.add(getID)  // index = 8
                    }

                }
                .addOnFailureListener { e ->
                    Log.d("VehicleError", e.toString())
                }
        }
        return userArray
    }

    private fun getOwnerDetails(documentId:String): ArrayList<String> {
        var userArray = arrayListOf<String>()
        //val currentUser = auth.currentUser?.email
        Log.d("Vehicle",documentId.toString())

        if (documentId!= null) {
            var collectionName = "usertype"
            //var email = currentUser.toString()

            val db = FirebaseFirestore.getInstance()
            val collectionRef = db.collection(collectionName)
            val documentRef = collectionRef.document(documentId)

            documentRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if(documentSnapshot.exists()){
                        val data = documentSnapshot.data
                        val getEmail = data?.get("email")
                        val getUserType = data?.get("userType")
                        val getFirstName = data?.get("firstName")
                        val getMiddleName = data?.get("middleName")
                        val getLastName = data?.get("lastName")
                        val getAge = data?.get("age")
                        val getHouseNo = data?.get("houseNo")
                        val getStreet = data?.get("street")
                        val getBuildingSubdivision = data?.get("buildingSubdivision")
                        val getBarangay = data?.get("barangay")
                        val getCity = data?.get("city")
                        val getProvince = data?.get("province")
                        val getZipCode = data?.get("zipCode")
                        val getCellphoneNo = data?.get("cellphoneNo")
                        val getTelephoneNo = data?.get("telephoneNo")
                        val getLicenseInfo = data?.get("licenseInfo")
                        val getID = documentSnapshot.id

                        //pass user info as array

                        userArray.add(getEmail.toString())
                        userArray.add(getUserType.toString())
                        val fullName = "$getFirstName $getMiddleName $getLastName"
                        userArray.add(fullName)
                        userArray.add(getAge.toString())
                        val location = "$getCity, $getProvince ($getZipCode)"
                        userArray.add(location)
                        val fullAddress = "$getHouseNo, $getStreet, $getBuildingSubdivision, $getBarangay, $getCity, $getProvince ($getZipCode)"
                        userArray.add(fullAddress)
                        val contactNumber = "$getCellphoneNo / $getTelephoneNo"
                        userArray.add(contactNumber)
                        userArray.add(getLicenseInfo.toString())
                        userArray.add(getID)
                    }


                }
                .addOnFailureListener { e ->
                    Log.d("VehicleError", e.toString())
                }
        }
        return userArray
    }
    private fun getLicenseDetail(documentId:String): ArrayList<String> {
        var userArray = arrayListOf<String>()
        //val currentUser = auth.currentUser?.email
        Log.d("Vehicle",documentId.toString())

        if (documentId!= null) {
            var collectionName = "licenseList"
            //var email = currentUser.toString()

            val db = FirebaseFirestore.getInstance()
            val collectionRef = db.collection(collectionName)
            //val documentRef = collectionRef.document(documentId)

            collectionRef.whereEqualTo("licenseOwner",documentId)
                .get()
                .addOnSuccessListener { QuerySnapshot ->
                    for(document in QuerySnapshot){
                        val data = document.data
                        val getLicenseOwner = data["licenseOwner"]
                        val getLicenseNo = data["licenseNo"]
                        val getExpirationDate = data["expirationDate"]
                        val getRestrictionCode = data["restrictionCode"]
                        val getLicenseClassification = data["licenseClassification"]
                        val getApplicableTransmissionType = data["applicableTransmissionType"]
                        val getPhysicalCondition = data["physicalCondition"]
                        val getID = document.id

                        //pass user info as array

                        userArray.add(getLicenseOwner.toString())
                        userArray.add(getLicenseNo.toString())
                        userArray.add(getExpirationDate.toString())
                        userArray.add(getRestrictionCode.toString())
                        userArray.add(getLicenseClassification.toString())
                        userArray.add(getApplicableTransmissionType.toString())
                        userArray.add(getPhysicalCondition.toString())
                        userArray.add(getID)
                    }


                }
                .addOnFailureListener { e ->
                    Log.d("VehicleError", e.toString())
                }
        }
        return userArray
    }
}