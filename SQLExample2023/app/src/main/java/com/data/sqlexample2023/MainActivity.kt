package com.data.sqlexample2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.data.sqlexample2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        viewData()
//
//        val sampleNote = Note(0,"First Note","This is my first Note!")
//        val sampleNote = Note(0,"Second Note","This is my Second Note!")
//        val sampleNote = Note(0,"Third Note","This is my Third Note!")
//        databaseHelper.insertNote(sampleNote)

        binding.button.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()
            addData(title,content)

        }

        binding.btnUpdate.setOnClickListener {
            val id = binding.etID.text.toString().toInt()
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            update(id,title,content)
        }

        binding.btnDelete.setOnClickListener {
            val id = binding.etID.text.toString().toInt()
            delete(id)
        }



    }

    private fun viewData(){
        var allData = databaseHelper.getAllNotes()
        var resultString = ""
        for (note in allData){
            resultString += "Note : ${note.id} ${note.title} ${note.content}\n"
        }

        binding.txtresult.text = resultString
    }

    private fun addData(title:String,content:String){
        val sampleNote = Note(0,title,content)
        databaseHelper.insertNote(sampleNote)
        viewData()

        Toast.makeText(applicationContext, "New Note Added", Toast.LENGTH_SHORT).show()
    }

    private fun update(id:Int,title: String,content: String){
        val noteObject = Note(id,title,content)
        databaseHelper.updateData(noteObject)
        viewData()
        Toast.makeText(applicationContext, "Note Updated", Toast.LENGTH_SHORT).show()
    }

    private fun delete(id: Int){
        databaseHelper.deleteData(id)
        viewData()
        Toast.makeText(applicationContext, "Note Deleted", Toast.LENGTH_SHORT).show()
    }


}