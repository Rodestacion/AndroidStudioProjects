package com.data.cloudstoragedemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.data.cloudstoragedemo.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var storageRef: StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var imageUri:Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initStorage()

        binding.imgSelect.setOnClickListener {
            resultLauncher.launch("image/*")
        }

        binding.btnUpload.setOnClickListener {
            uploadImage()
        }

        binding.btnShowAll.setOnClickListener {
            var nextScreen = Intent(this@MainActivity,DisplayActivity::class.java)
            startActivity(nextScreen)
            //finish()
        }
//        val db = Firebase.firestore
//        var storageRef = storage.reference
//
//        val user = hashMapOf(
//            "first" to "Ada",
//            "last" to "Lovelace",
//            "born" to 1815
//        )
//
//        // Add a new document with a generated ID
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Toast.makeText(applicationContext, "DocumentSnapshot added with ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener { e ->
//                Toast.makeText(applicationContext, "Error adding document", Toast.LENGTH_SHORT).show()
//            }
    }

    private fun uploadImage() {
        binding.imgSelect.isEnabled = false
        binding.btnUpload.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE
        storageRef = storageRef.child(System.currentTimeMillis().toString())

        imageUri?.let {
            storageRef.putFile(it).addOnCompleteListener{task->
                if (task.isSuccessful){
                    storageRef.downloadUrl.addOnSuccessListener {uri->
                        var imageDescription = binding.etDescription.text.toString()
                        var imageLink = uri.toString()

                        val db = Firebase.firestore

                        val details = hashMapOf(
                            "imageDescription" to imageDescription,
                            "imageLink" to imageLink
                        )

                        db.collection("image_data")
                            .add(details)
                            .addOnSuccessListener { _ ->
                                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()

                                binding.imgSelect.isEnabled = true
                                binding.btnUpload.isEnabled = true
                                binding.etDescription.setText("")
                                binding.progressBar.visibility = View.INVISIBLE
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(applicationContext, "FAILED", Toast.LENGTH_SHORT).show()

                                binding.imgSelect.isEnabled = true
                                binding.btnUpload.isEnabled = true
                                binding.progressBar.visibility = View.INVISIBLE
                            }



                    }

                }else{
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()

                    binding.imgSelect.isEnabled = true
                    binding.btnUpload.isEnabled = true
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }



    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        imageUri =it
        binding.imageView.setImageURI(it)
    }

    private fun initStorage(){
        //initialize firebase objects
        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }
}