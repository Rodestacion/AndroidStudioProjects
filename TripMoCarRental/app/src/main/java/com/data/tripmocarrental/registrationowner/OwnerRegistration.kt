package com.data.tripmocarrental.registrationowner

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DeadObjectException
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import com.data.tripmocarrental.R
import com.data.tripmocarrental.common.ContactInformationFragment
import com.data.tripmocarrental.common.ProfileFragment
import com.data.tripmocarrental.common.SplashScreen
import com.data.tripmocarrental.common.SupportingDocumentFragment
import com.data.tripmocarrental.common.SupportingIdFragment
import com.data.tripmocarrental.databinding.ActivityOwnerRegistrationBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class OwnerRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityOwnerRegistrationBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var userInfo: ArrayList<String>
    private lateinit var f1: ProfileFragment
    private lateinit var f2: ContactInformationFragment
    private lateinit var f3: OwnerVehicleInformationFragment
    private lateinit var f4: SupportingIdFragment
    private lateinit var f5: SupportingDocumentFragment
    private lateinit var storageRef: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        initializeFirebase()

        //pass User Information during Registration
        userInfo = intent.getStringArrayListExtra("userInfo")!!

        //initialize fragment
        f1 = ProfileFragment()
        f2 = ContactInformationFragment()
        f3 = OwnerVehicleInformationFragment()
        f4 = SupportingIdFragment()
        f5 = SupportingDocumentFragment()

        initializeFragment()

        //Move on next fragment
        f1.onNextProcess = {
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val basicInfo = bundle.getStringArrayList("basicInfoKey")
                profileInfoAdd(basicInfo)
            }
        }

        f2.onNextProcess = {
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val contactInfo = bundle.getStringArrayList("contactInfoKey")
                contactInfoAdd(contactInfo)
            }
        }

        f3.onNextProcess = {
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val vehicleInfo = bundle.getStringArrayList("vehicleInfoKey")
                addVehicleInfo(vehicleInfo)
            }

            supportFragmentManager.beginTransaction().apply {
                binding.progressBar2.progress = 75
                replace(R.id.ownerFragmentContainerView,f4)
                commit()
            }
        }

        f4.onNextProcess={
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                binding.progressBar.visibility = View.VISIBLE
                val getDocUri = bundle.getString("IDKey")
                val imageIDUri = getDocUri.toString().toUri()

                supportingIDDocumentAdd(imageIDUri)
            }

        }
        f5.onNextProcess={
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                binding.progressBar.visibility = View.VISIBLE
                val getDocUri = bundle.getString("documentKey")
                val imageDocUri = getDocUri.toString().toUri()

                supportingAddressDocumentAdd(imageDocUri)
            }

        }


    }

//    override fun onResume() {
//        super.onResume()
//        initializeFragment()
//    }

    private fun initializeFragment() {
        supportFragmentManager.beginTransaction().apply {
            when(userInfo.elementAt(3).toString()){
                "first"->{
                    replace(R.id.ownerFragmentContainerView,f1)
                    commit()
                }
                "second"->{
                    binding.progressBar2.progress = 20
                    replace(R.id.ownerFragmentContainerView,f2)
                    commit()
                }
                "third"->{
                    binding.progressBar2.progress = 40
                    replace(R.id.ownerFragmentContainerView,f3)
                    commit()
                }
                "fourth"->{
                    binding.progressBar2.progress = 60
                    replace(R.id.ownerFragmentContainerView,f4)
                    commit()
                }
                "fifth"->{
                    binding.progressBar2.progress = 80
                    replace(R.id.ownerFragmentContainerView,f5)
                    commit()
                }
            }
        }
    }

    private fun initializeFirebase(){
        // Initialize Firebase
        firestore = FirebaseFirestore.getInstance()
        storageRef = FirebaseStorage.getInstance().reference.child("BorrowerDocs")
    }

    override fun onPause() {
        super.onPause()
        initializeFirebase()
    }


    private fun profileInfoAdd(basicInfo: ArrayList<String>?) {
        val myID = userInfo.elementAt(4)

        val firstName = basicInfo!!.elementAt(0)
        val middleName = basicInfo.elementAt(1)
        val lastName = basicInfo.elementAt(2)
        val age = basicInfo.elementAt(3).toInt()
        val birthday = basicInfo.elementAt(4)

        val db = FirebaseFirestore.getInstance()
        val userTypeRef = db.collection("usertype")
        val documentRef = userTypeRef.document(myID)

        val profile = hashMapOf<String, Any?>(
            "firstName" to firstName,
            "middleName" to middleName,
            "lastName" to lastName,
            "age" to age,
            "birthday" to birthday,
            "profileStatus" to "second"
        )

        documentRef.update(profile)
            .addOnSuccessListener {
                db.clearPersistence()
                Toast.makeText(applicationContext,"Basic Info Update Successful",Toast.LENGTH_SHORT).show()

                supportFragmentManager.beginTransaction().apply {
                    binding.progressBar2.progress = 20
                    replace(R.id.ownerFragmentContainerView, f2)
                    commit()
                }
            }
            .addOnFailureListener {
                db.clearPersistence()
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
    }

    private fun contactInfoAdd(contactInfo: ArrayList<String>?) {
        val myID = userInfo.elementAt(4)

        val houseNo = contactInfo!!.elementAt(0)
        val street = contactInfo.elementAt(1)
        val buildingSubdivision = contactInfo.elementAt(2)
        val barangay = contactInfo.elementAt(3)
        val city = contactInfo.elementAt(4)
        val province = contactInfo.elementAt(5)
        val zipCode = contactInfo.elementAt(6).toInt()
        val cellphoneNo = contactInfo.elementAt(7)
        val telephoneNo = contactInfo.elementAt(8)

        val db = FirebaseFirestore.getInstance()
        val userTypeRef = db.collection("usertype")
        val documentRef = userTypeRef.document(myID)

        val contacts = hashMapOf<String,Any?>(
            "houseNo" to houseNo,
            "street" to street,
            "buildingSubdivision" to buildingSubdivision,
            "barangay" to barangay,
            "city" to city,
            "province" to province,
            "zipCode" to zipCode,
            "cellphoneNo" to cellphoneNo,
            "telephoneNo" to telephoneNo,
            "profileStatus" to "third"
        )

        documentRef.update(contacts)
            .addOnSuccessListener {
                db.clearPersistence()
                Toast.makeText(applicationContext, "Contact Info Update Successful", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().apply {
                    binding.progressBar2.progress = 40
                    replace(R.id.ownerFragmentContainerView,f3)
                    commit()
                }
            }
            .addOnFailureListener {
                db.clearPersistence()
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
    }

    //First Vehicle Registration
    private fun addVehicleInfo(vehicleInfo: ArrayList<String>?) {
        val myID = userInfo.elementAt(4)

        val vehicleBrand = vehicleInfo!!.elementAt(0)
        val vehicleModel = vehicleInfo.elementAt(1)
        val vehicleCapacity = vehicleInfo.elementAt(2).toInt()
        val vehicleCategory = vehicleInfo.elementAt(3)
        val vehicleTransmission = vehicleInfo.elementAt(4)
        val vehiclePlateNo = vehicleInfo.elementAt(5)
        val vehicleCerfRegNo = vehicleInfo.elementAt(6).toInt()
        val vehicleRegisterDate = vehicleInfo.elementAt(7)
        val vehicleDriveMode = vehicleInfo.elementAt(8)

        val db = FirebaseFirestore.getInstance()
        val userTypeRef = db.collection("usertype")
        val documentRef = userTypeRef.document(myID)

        val vehicle = hashMapOf<String,Any?>(
            "vehicleBrand" to vehicleBrand,
            "vehicleModel" to vehicleModel,
            "vehicleCapacity" to vehicleCapacity,
            "vehicleCategory" to vehicleCategory,
            "vehicleTransmission" to vehicleTransmission,
            "vehiclePlateNo" to vehiclePlateNo,
            "vehicleCerfRegNo" to vehicleCerfRegNo,
            "vehicleRegisterDate" to vehicleRegisterDate,
            "vehicleDriveMode" to vehicleDriveMode,
            "profileStatus" to "fourth"
        )

        documentRef.update(vehicle)
            .addOnSuccessListener {
                db.clearPersistence()
                Toast.makeText(applicationContext, "Contact Info Update Successful", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().apply {
                    binding.progressBar2.progress = 60
                    replace(R.id.ownerFragmentContainerView,f4)
                    commit()
                }
            }
            .addOnFailureListener {
                db.clearPersistence()
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
    }

    private fun supportingIDDocumentAdd(imageIDUri: Uri) {
        val myID = userInfo.elementAt(4)
        val filename:String = "${myID}_userID"
        storageRef = FirebaseStorage.getInstance().reference.child("OwnerDocs")

        storageRef = storageRef.child(filename)
        imageIDUri?.let {
            storageRef.putFile(it).addOnCompleteListener{task->
                if (task.isSuccessful){
                    storageRef.downloadUrl

                        .addOnSuccessListener {uri->
                            var imageLink = uri.toString()

                            val db = FirebaseFirestore.getInstance()
                            val userTypeRef = db.collection("usertype")
                            val documentRef = userTypeRef.document(myID)

                            val details = hashMapOf<String, Any?>(
                                "imageIDLink" to imageLink,
                                "profileStatus" to "fifth"
                            )

                            documentRef.update(details)
                                .addOnSuccessListener {
                                    db.clearPersistence()
                                    binding.progressBar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "ID Upload Successful", Toast.LENGTH_SHORT).show()

                                    supportFragmentManager.beginTransaction().apply {
                                        binding.progressBar2.progress = 80
                                        replace(R.id.ownerFragmentContainerView,f5)
                                        commit()
                                    }

                                }
                                .addOnFailureListener {
                                    db.clearPersistence()
                                    binding.progressBar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()

                                }
                        }

                }else{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    private fun supportingAddressDocumentAdd(imageDocUri: Uri) {
        val myID = userInfo.elementAt(4)
        val filename:String = "${myID}_userAddress"
        storageRef = FirebaseStorage.getInstance().reference.child("OwnerDocs")

        storageRef = storageRef.child(filename)
        imageDocUri?.let {
            storageRef.putFile(it).addOnCompleteListener{task->
                if (task.isSuccessful){
                    storageRef.downloadUrl.addOnSuccessListener {uri->
                        var imageLink = uri.toString()

                        val db = FirebaseFirestore.getInstance()
                        val userTypeRef = db.collection("usertype")
                        val documentRef = userTypeRef.document(myID)

                        val details = hashMapOf<String, Any?>(
                            "imageAddressLink" to imageLink,
                            "profileComplete" to true,
                            "profileStatus" to "done"
                        )
                        documentRef.update(details)
                            .addOnSuccessListener {
                                db.clearPersistence()
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Document Upload Successful", Toast.LENGTH_SHORT).show()

                                val nextScreen = Intent(this,SplashScreen::class.java)
                                nextScreen.putExtra("userInfo",userInfo)
                                startActivity(nextScreen)
                                finish()

                            }
                            .addOnFailureListener {
                                db.clearPersistence()
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
                            }
                    }

                }else{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}