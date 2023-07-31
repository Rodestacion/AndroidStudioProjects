package com.data.apexercise12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.data.apexercise12.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun addData(measurement: MeasurementLog){ //Students){
        Firebase.firestore.collection("measurement")
            .add(measurement)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                //Log.d("SUCCESS_TAG","Success!")
            }
            .addOnFailureListener {e->
                Toast.makeText(applicationContext, "Failed! $e", Toast.LENGTH_SHORT).show()
                //Log.e("SUCCESS_TAG","Failed! $e")
            }

    }

    private fun getOneData(collectionName:String, documentId:String){
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection(collectionName)
        val documentRef = collectionRef.document(documentId)

        documentRef.get()
            .addOnSuccessListener {documentSnapshot->
                if(documentSnapshot.exists()){
                    val measurement = documentSnapshot.data
                    val message = "Measurements: $measurement"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Document does not exist", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }

    }

    private fun getAllData(collectionName:String){
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection(collectionName)

        collectionRef.get()
            .addOnSuccessListener {QuerySnapshot->
                for(documentSnapshot in QuerySnapshot){
                    val measurement = documentSnapshot.data
                    val message = "Measurements: $measurement"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }

    }

    private fun updateData(collectionName:String, documentId:String){
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection(collectionName)
        val documentRef = collectionRef.document(documentId)

        val newMeasurementData = hashMapOf<String,Any?>(
            "age" to 9,
            "gradeLevel" to "Grade 3"
        )
        documentRef.update(newMeasurementData)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Document Update Successful", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }

    }

    private fun deleteData(collectionName:String, documentId:String){
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection(collectionName)
        val documentRef = collectionRef.document(documentId)

        documentRef.delete()
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Document Delete Successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
            }

    }
}