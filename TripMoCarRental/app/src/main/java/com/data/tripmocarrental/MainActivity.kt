package com.data.tripmocarrental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.data.tripmocarrental.borrower.home.BorrowerHomeFragment
import com.data.tripmocarrental.borrower.BorrowerProfileFragment
import com.data.tripmocarrental.borrower.BorrowerReservationScheduleFragment
import com.data.tripmocarrental.borrower.SearchVehicleFragment
import com.data.tripmocarrental.databinding.ActivityMainBinding
import com.data.tripmocarrental.vehicleowner.OwnerHomeFragment
import com.data.tripmocarrental.vehicleowner.OwnerReservationScheduleFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var f1:Fragment
    private lateinit var f2:Fragment
    private lateinit var f3:Fragment
    private lateinit var f4:Fragment
    private lateinit var userInfo:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase & user details
        auth = Firebase.auth
        userInfo = getUserDetails()

        Handler().postDelayed({
            Log.d("VehicleUser",userInfo.elementAt(1))
            if(userInfo.elementAt(1)=="borrower"){
                f1 = BorrowerHomeFragment()
                f2 = SearchVehicleFragment()
                f3 = BorrowerReservationScheduleFragment()
                f4 = BorrowerProfileFragment()
            }else{
                f1 = OwnerHomeFragment()
                f2 = SearchVehicleFragment()
                f3 = OwnerReservationScheduleFragment()
                f4 = BorrowerProfileFragment()
            }
        },1000)



        Handler().postDelayed({
            supportFragmentManager.beginTransaction().apply {
                var myBundle = Bundle()
                myBundle.putStringArrayList("userInfo",userInfo)
                f1.arguments = myBundle
                replace(R.id.borrowerMainFragmentContainerView,f1)
                commit()
            }
        },1000)

        binding.floatingActionButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationBar.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navHome->{
                    binding.drawerLayout.close()
                    supportFragmentManager.beginTransaction().apply {
                        var myBundle = Bundle()
                        myBundle.putStringArrayList("userInfo",userInfo)
                        f1.arguments = myBundle
                        replace(R.id.borrowerMainFragmentContainerView,f1)
                        commit()
                    }
                }
                R.id.navSearch->{
                    if(userInfo.elementAt(1)=="borrower"){
                        Log.d("Vehicle2",userInfo.toString())
                        binding.drawerLayout.close()
                        supportFragmentManager.beginTransaction().apply {
                            var myBundle = Bundle()
                            myBundle.putStringArrayList("userInfo",userInfo)
                            f2.arguments  = myBundle
                            replace(R.id.borrowerMainFragmentContainerView,f2)
                            commit()
                        }
                    }else{
                        Toast.makeText(applicationContext, "Sorry button is not available for the account", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.navSchedule->{
                    binding.drawerLayout.close()
                    supportFragmentManager.beginTransaction().apply {
                        var myBundle = Bundle()
                        myBundle.putStringArrayList("userInfo",userInfo)
                        f3.arguments = myBundle
                        replace(R.id.borrowerMainFragmentContainerView,f3)
                        commit()
                    }
                }
                R.id.navProfile->{
                    binding.drawerLayout.close()
                    supportFragmentManager.beginTransaction().apply {
                        var myBundle = Bundle()
                        myBundle.putStringArrayList("userInfo",userInfo)
                        f4.arguments = myBundle
                        replace(R.id.borrowerMainFragmentContainerView,f4)
                        commit()
                    }
                }
                R.id.navLogout->{
                    userLogout()
                }
            }
            true
        }


    }

    private fun userLogout(){
        auth.signOut()
        val nextScreen = Intent(this,Login::class.java)
        startActivity(nextScreen)
        finish()
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
}
