package com.data.sqlexample2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.sqlexample2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        val sampleNote = Note(0,"First Note","This is my first Note!")
        databaseHelper.insertNote(sampleNote)





    }
}