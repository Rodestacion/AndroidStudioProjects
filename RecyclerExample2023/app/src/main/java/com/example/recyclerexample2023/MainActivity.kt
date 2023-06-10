package com.example.recyclerexample2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerexample2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.myRecyclerView
        recyclerView.layoutManager =LinearLayoutManager(this)

        val items = listOf(
            Item("Laptop","Portable Computer"),
            Item("Mouse","Pointing Device"),
            Item("Keyboard","Input Device"),
            Item("Laptop","Portable Computer"),
            Item("Mouse","Pointing Device"),
            Item("Keyboard","Input Device"),
            Item("Laptop","Portable Computer"),
            Item("Mouse","Pointing Device"),
            Item("Keyboard","Input Device"),
            Item("Laptop","Portable Computer"),
            Item("Mouse","Pointing Device"),
            Item("Keyboard","Input Device")
        )

        recyclerView.adapter = ItemAdapter(items)


    }
}