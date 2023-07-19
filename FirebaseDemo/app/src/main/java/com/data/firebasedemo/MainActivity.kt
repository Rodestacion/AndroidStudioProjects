package com.data.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.data.firebasedemo.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firestore:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        //var myStudents = Students("Peter Parker","Grade1",7)
        //var myStudents = Students("MJ Watson","Grade1",8)
        //addData(myStudents)

        //getOneData("students","tS7yhz07FiPyhjJqJicz")
        //getAllData("students")

        //deleteData("students","F1p85sOGDJknuKAjBnMW")
        updateData("students","tS7yhz07FiPyhjJqJicz")


    }
    private fun addData(students: Students){
        Firebase.firestore.collection("students")
            .add(students)
            .addOnSuccessListener {
                Log.d("SUCCESS_TAG","Success!")
            }
            .addOnFailureListener {e->
                Log.e("SUCCESS_TAG","Failed! $e")
            }

    }

    private fun getOneData(collectionName:String, documentId:String){
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection(collectionName)
        val documentRef = collectionRef.document(documentId)

        documentRef.get()
            .addOnSuccessListener {documentSnapshot->
                if(documentSnapshot.exists()){
                    val student = documentSnapshot.data
                    val message = "Students: $student"
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
                    val student = documentSnapshot.data
                    val message = "Student: $student"
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

        val newStudentData = hashMapOf<String,Any?>(
            "age" to 9,
            "gradeLevel" to "Grade 3"
        )
        documentRef.update(newStudentData)
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