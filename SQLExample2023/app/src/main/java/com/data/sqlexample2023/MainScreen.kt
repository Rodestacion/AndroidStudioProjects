package com.data.sqlexample2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.sqlexample2023.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity() {
    //global
    private lateinit var binding:ActivityMainScreenBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView:RecyclerView
    private lateinit var noteList :MutableList<Note>
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //object instantiation
        databaseHelper = DatabaseHelper(this)
        
        //setup the recyclerview
        recyclerView = binding.recyclerView
        
        //add layout to recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        //declare data
        noteList = getData()
        recyclerView.adapter = NotesAdapter(noteList)

        //add item
        binding.btnAddNew.setOnClickListener {
            var title = binding.etTitleNew.text.toString()
            var content = binding.etContentNew.text.toString()
            var newNote = Note(0,title,content)
            //add new data to database table
            addData(newNote)

            //add new note to list
            noteList.add(newNote)

            //notify adapter that data has changed
            recyclerView.adapter?.notifyDataSetChanged()

            binding.etTitleNew.text.clear()
            binding.etContentNew.text.clear()
        }


    }

    private fun getData(): MutableList<Note> {
        return databaseHelper.getAllNotes()
    }

    private fun addData(note:Note){
        databaseHelper.insertNote(note)
        Toast.makeText(applicationContext, "New Note Added", Toast.LENGTH_SHORT).show()
    }

    private fun update(id:Int,title: String,content: String){
        val noteObject = Note(id,title,content)
        databaseHelper.updateData(noteObject)
        getData()
        Toast.makeText(applicationContext, "Note Updated", Toast.LENGTH_SHORT).show()
    }

    private fun delete(id: Int){
        databaseHelper.deleteData(id)
        getData()
        Toast.makeText(applicationContext, "Note Deleted", Toast.LENGTH_SHORT).show()
    }
}