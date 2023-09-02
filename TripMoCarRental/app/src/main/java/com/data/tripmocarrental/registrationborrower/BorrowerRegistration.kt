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
import com.data.tripmocarrental.databinding.ActivityBorrowerRegistrationBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    private lateinit var f4:SupportingDocumentFragment
    private var docUploadSuccess1: Boolean = false
    private var docUploadSuccess2: Boolean = false
    private var imageUri: Uri?=null
    private lateinit var storageRef: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrowerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        firestore = FirebaseFirestore.getInstance()
        storageRef = FirebaseStorage.getInstance().reference.child("Images")

        // Initialize Fragment
        f1 = ProfileFragment()
        f2 = ContactInformationFragment()
        f3 = BorrowerOtherInformationFragment()
        f4 = SupportingDocumentFragment()

        //pass User Information during Registration
        userInfo = intent.getStringArrayListExtra("userInfo")!!

        //initialize default Borrower Registration Form fragment
        supportFragmentManager.beginTransaction().apply {
            when(userInfo.elementAt(3).toString()){
                "first"->{
                    replace(R.id.borrowerFragmentContainerView,f1)
                    commit()
                }
                "second"->{
                    binding.progressBar3.progress = 25
                    replace(R.id.borrowerFragmentContainerView,f2)
                    commit()
                }
                "third"->{
                    binding.progressBar3.progress = 50
                    replace(R.id.borrowerFragmentContainerView,f3)
                    commit()
                }
                "fourth"->{
                    binding.progressBar3.progress = 75
                    replace(R.id.borrowerFragmentContainerView,f4)
                    commit()
                }
            }
        }

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
                val result = bundle.getString("documentKey")?.toUri()
                imageUri = result
                supportingIDDocumentAdd()
                Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
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
                    binding.progressBar3.progress = 25
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
                    binding.progressBar3.progress = 50
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
                    binding.progressBar3.progress = 75
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
                    binding.progressBar3.progress = 75
                    replace(R.id.borrowerFragmentContainerView,f4)
                    commit()
                }
            }
            .addOnFailureListener {

            }
    }

    private fun supportingIDDocumentAdd(){
        val myID = userInfo.elementAt(4)
        val filename:String = "${myID}_userID"

        storageRef = storageRef.child(filename)
        imageUri?.let {
            storageRef.putFile(it).addOnCompleteListener{task->
                if (task.isSuccessful){
                    storageRef.downloadUrl.addOnSuccessListener {uri->
                        var imageLink = uri.toString()

                        val db = FirebaseFirestore.getInstance()
                        val userTypeRef = db.collection("usertype")
                        val documentRef = userTypeRef.document(myID)

                        val details = hashMapOf<String, Any?>(
                            "imageLink" to imageLink
                        )

                        documentRef.update(details)
                            .addOnSuccessListener {
                                docUploadSuccess1 = true
                                //Toast.makeText(applicationContext, "Supporting Document Upload Successful", Toast.LENGTH_SHORT).show()

//                                val nextScreen = Intent(this,SplashScreen::class.java)
//                                startActivity(nextScreen)
//                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
                            }
                    }

                }else{
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}