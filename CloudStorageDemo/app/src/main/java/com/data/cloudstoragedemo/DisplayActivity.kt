package com.data.cloudstoragedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.cloudstoragedemo.databinding.ActivityDisplayBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class DisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var adapter: ImageItemAdapter
    private var mList = mutableListOf<ImageItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //declare recyclerview
        recyclerView = binding.myRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        //declare firebase
        val db = Firebase.firestore
        db.collection("image_data")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var newImageItem = ImageItem(document.data["imageDescription"].toString(),document.data["imageLink"].toString())
                    mList.add(newImageItem)
                }
                recyclerView.adapter = ImageItemAdapter(mList)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(applicationContext, "Failed to Retrieve Data", Toast.LENGTH_SHORT).show()
            }

    }
}