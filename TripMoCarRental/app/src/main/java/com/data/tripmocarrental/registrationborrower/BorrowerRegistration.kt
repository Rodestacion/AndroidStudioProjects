package com.data.tripmocarrental.registrationborrower

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import com.data.tripmocarrental.R
import com.data.tripmocarrental.common.ContactInformationFragment
import com.data.tripmocarrental.common.ProfileFragment
import com.data.tripmocarrental.common.SplashScreen
import com.data.tripmocarrental.common.SupportingDocumentFragment
import com.data.tripmocarrental.common.SupportingIdFragment
import com.data.tripmocarrental.databinding.ActivityBorrowerRegistrationBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.ArrayList

class BorrowerRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityBorrowerRegistrationBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var userInfo: ArrayList<String>
    private lateinit var f1:ProfileFragment
    private lateinit var f2:ContactInformationFragment
    private lateinit var f3:BorrowerOtherInformationFragment
    private lateinit var f4: SupportingIdFragment
    private lateinit var f5:SupportingDocumentFragment
    private lateinit var storageRef: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrowerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        firestore = FirebaseFirestore.getInstance()
        storageRef = FirebaseStorage.getInstance().reference.child("BorrowerDocs")

        // Initialize Fragment
        f1 = ProfileFragment()
        f2 = ContactInformationFragment()
        f3 = BorrowerOtherInformationFragment()
        f4 = SupportingIdFragment()
        f5 = SupportingDocumentFragment()

        //pass User Information during Registration
        userInfo = intent.getStringArrayListExtra("userInfo")!!

        //initialize default Borrower Registration Form fragment
        initializeFragment()

        //Move on next fragment
        f1.onNextProcess = {

            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val basicInfo = bundle.getStringArrayList("basicInfoKey")
                profileInfoAdd(basicInfo)
            }
        }

        f2.onNextProcess={
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val contactInfo = bundle.getStringArrayList("contactInfoKey")
                contactInfoAdd(contactInfo)
            }
        }

        f3.onWithLicenseProcess={
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val withLicenseInfo = bundle.getStringArrayList("withLicenseInfoKey")
                licenseInfoAdd(withLicenseInfo)
            }
        }

        f3.onWithoutLicenseProcess={
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                val noLicenseInfo = bundle.getBoolean("noLicenseInfoKey")
                noLicenseInfoAdd(noLicenseInfo)
            }
        }

        f4.onNextProcess={
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                binding.progressBar5.visibility = View.VISIBLE
                val getDocUri = bundle.getString("IDKey")
                val imageIDUri = getDocUri.toString().toUri()

                supportingIDDocumentAdd(imageIDUri)
            }

        }
        f5.onNextProcess={
            supportFragmentManager.setFragmentResultListener("requestKey",this){ _, bundle ->
                binding.progressBar5.visibility = View.VISIBLE
                val getDocUri = bundle.getString("documentKey")
                val imageDocUri = getDocUri.toString().toUri()

                supportingAddressDocumentAdd(imageDocUri)
            }

        }
    }
//     On resume return of previous fragment when browse image
//    override fun onResume() {
//        super.onResume()
//        initializeFragment()
//    }

    private fun initializeFragment(){
        supportFragmentManager.beginTransaction().apply {
            when(userInfo.elementAt(3).toString()){
                "first"->{
                    replace(R.id.borrowerFragmentContainerView,f1)
                    commit()
                }
                "second"->{
                    binding.progressBar3.progress = 20
                    replace(R.id.borrowerFragmentContainerView,f2)
                    commit()
                }
                "third"->{
                    binding.progressBar3.progress = 40
                    replace(R.id.borrowerFragmentContainerView,f3)
                    commit()
                }
                "fourth"->{
                    binding.progressBar3.progress = 60
                    replace(R.id.borrowerFragmentContainerView,f4)
                    commit()
                }
                "fifth"->{
                    binding.progressBar3.progress = 80
                    replace(R.id.borrowerFragmentContainerView,f5)
                    commit()
                }
            }
        }
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

        val profile = hashMapOf<String,Any?>(
            "firstName" to firstName,
            "middleName" to middleName,
            "lastName" to lastName,
            "age" to age,
            "birthday" to birthday,
            "profileStatus" to "second"
        )

        documentRef.update(profile)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Basic Info Update Successful", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().apply {
                    binding.progressBar3.progress = 20
                    replace(R.id.borrowerFragmentContainerView,f2)
                    commit()
                }
            }
            .addOnFailureListener {
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
                Toast.makeText(applicationContext, "Contact Info Update Successful", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().apply {
                    binding.progressBar3.progress = 40
                    replace(R.id.borrowerFragmentContainerView,f3)
                    commit()
                }
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
    }

    private fun noLicenseInfoAdd(noLicenseInfo: Boolean) {
        val myID = userInfo.elementAt(4)

        val db = FirebaseFirestore.getInstance()
        val userTypeRef = db.collection("usertype")
        val documentRef = userTypeRef.document(myID)

        val license = hashMapOf<String, Any?>(
            "licenseInfo" to noLicenseInfo,
            "profileStatus" to "fourth"
        )

        documentRef.update(license)
            .addOnSuccessListener {
                Toast.makeText(applicationContext,"License Info Update Successful",Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().apply {
                    binding.progressBar3.progress = 60
                    replace(R.id.borrowerFragmentContainerView,f4)
                    commit()
                }
            }
            .addOnFailureListener {

            }
    }

    private fun licenseInfoAdd(withLicenseInfo: ArrayList<String>?){
        val myID = userInfo.elementAt(4)

        val licenseInfo = withLicenseInfo!!.elementAt(0).toBoolean()
        val licenseNo = withLicenseInfo.elementAt(1)
        val expirationDate = withLicenseInfo.elementAt(2)
        val restrictionCode = withLicenseInfo.elementAt(3)
        val licenseClassification = withLicenseInfo.elementAt(4)
        val applicableTransmissionType = withLicenseInfo.elementAt(5)
        val physicalCondition = withLicenseInfo.elementAt(6)

        val db = FirebaseFirestore.getInstance()
        val userTypeRef = db.collection("usertype")
        val documentRef = userTypeRef.document(myID)

        val license = hashMapOf<String, Any?>(
            "licenseInfo" to licenseInfo,
            "licenseNo" to licenseNo,
            "expirationDate" to expirationDate,
            "restrictionCode" to restrictionCode,
            "licenseClassification" to licenseClassification,
            "applicableTransmissionType" to applicableTransmissionType,
            "physicalCondition" to physicalCondition,
            "profileStatus" to "fourth"
        )

        documentRef.update(license)
            .addOnSuccessListener {
                Toast.makeText(applicationContext,"License Info Update Successful",Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().apply {
                    binding.progressBar3.progress = 60
                    replace(R.id.borrowerFragmentContainerView,f4)
                    commit()
                }
            }
            .addOnFailureListener {

            }
    }
//    var imageRef = Firebase.storage.reference
//    private fun deleteImage() = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            imageRef.child("Images/40ayATiiQg1rOrufSnuD_userID").delete().await()
//            withContext(Dispatchers.Main){
//                Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_SHORT).show()
//            }
//
//        }catch (e:Exception){
//            withContext(Dispatchers.Main){
//                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun supportingIDDocumentAdd(imageIDUri: Uri) {
        val myID = userInfo.elementAt(4)
        val filename:String = "${myID}_userID"
        storageRef = FirebaseStorage.getInstance().reference.child("BorrowerDocs")

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
                                    binding.progressBar5.visibility = View.GONE
                                    Toast.makeText(applicationContext, "ID Upload Successful", Toast.LENGTH_SHORT).show()

                                    supportFragmentManager.beginTransaction().apply {
                                        binding.progressBar3.progress = 80
                                        replace(R.id.borrowerFragmentContainerView,f5)
                                        commit()
                                    }

                                }
                                .addOnFailureListener {
                                    binding.progressBar5.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()

                                }
                        }

                }else{
                    binding.progressBar5.visibility = View.GONE
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    private fun supportingAddressDocumentAdd(imageDocUri: Uri) {
        val myID = userInfo.elementAt(4)
        val filename:String = "${myID}_userAddress"
        storageRef = FirebaseStorage.getInstance().reference.child("BorrowerDocs")

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
                                    binding.progressBar5.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Document Upload Successful", Toast.LENGTH_SHORT).show()

                                    val nextScreen = Intent(this,SplashScreen::class.java)
                                    nextScreen.putExtra("userInfo",userInfo)
                                    startActivity(nextScreen)
                                    finish()

                                }
                                .addOnFailureListener {
                                    binding.progressBar5.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
                                }
                    }

                }else{
                    binding.progressBar5.visibility = View.GONE
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}