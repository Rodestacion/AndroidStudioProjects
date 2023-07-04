package com.data.sqlexample2023

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.sqlexample2023.databinding.ActivityMainScreenBinding
import com.data.sqlexample2023.databinding.DialogLayoutBinding

class MainScreen : AppCompatActivity() {
    //global
    private lateinit var binding:ActivityMainScreenBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView:RecyclerView
    private lateinit var noteList :MutableList<Note>
    private lateinit var adapter: NotesAdapter
    

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
        //initialize adapter object
        adapter = NotesAdapter(noteList)
        //recyclerView.adapter = NotesAdapter(noteList)
        recyclerView.adapter = adapter

        //delete
        adapter.onDeleteClick={note->
            showDeleteDialog(note)
            //Toast.makeText(applicationContext, "${note.id}", Toast.LENGTH_SHORT).show()
        }

        //Update
        adapter.onUpdateClick={note->
            showUpdateDialog(note)
            //Toast.makeText(applicationContext, "${note.id}", Toast.LENGTH_SHORT).show()
        }

//        //add item
//        binding.btnAddNew.setOnClickListener {
//            var title = binding.etTitleNew.text.toString()
//            var content = binding.etContentNew.text.toString()
//            var newNote = Note(0,title,content)
//            //add new data to database table
//            addData(newNote)
//
//            //add new note to list
//            noteList.add(newNote)
//
//            //notify adapter that data has changed
//            recyclerView.adapter?.notifyDataSetChanged()
//
//            binding.etTitleNew.text.clear()
//            binding.etContentNew.text.clear()
//        }

        binding.floatingActionButton.setOnClickListener {
            showAddDialog()
        }


    }


    private fun showAddDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Add New Note")

        val dialogLayout = layoutInflater.inflate(R.layout.dialog_layout,null)
        val dialogBinding = DialogLayoutBinding.bind(dialogLayout)
        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK"){dialog,_->
            val title = dialogBinding.etDialogTitle.text.toString()
            val content = dialogBinding.etDialogContent.text.toString()
            var newNote = Note(0,title,content)
            //add new data to database table
            addData(newNote)

            //add new note to list
            noteList.add(newNote)

            //notify adapter that data has changed
            recyclerView.adapter?.notifyDataSetChanged()
        }

        alertDialogBuilder.setNegativeButton("Cancel"){dialog, _->
            dialog.dismiss()
        }

        val alertDialog:AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    private fun showUpdateDialog(note: Note) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Edit Note")

        val dialogLayout = layoutInflater.inflate(R.layout.dialog_layout,null)
        val dialogBinding = DialogLayoutBinding.bind(dialogLayout)
        dialogBinding.etDialogTitle.setText(note.title)
        dialogBinding.etDialogContent.setText(note.content)

        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK"){dialog,_->
            val title = dialogBinding.etDialogTitle.text.toString()
            val content = dialogBinding.etDialogContent.text.toString()
            var newNote = Note(note.id,title,content)
            //update data to database table
            update(note.id,title,content)
            var temp: Int = 0
            //add new note to list
            repeat(noteList.size){
                if (noteList[it].id==note.id){
                    noteList[it].title = title
                    noteList[it].content = content
                    temp = it
                }
            }
            //notify adapter that data has changed
            recyclerView.adapter?.notifyItemChanged(temp)
        }

        alertDialogBuilder.setNegativeButton("Cancel"){dialog, _->
            dialog.dismiss()
        }

        val alertDialog:AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showDeleteDialog(note: Note) {
        val alertDialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Warning")
        alertDialogBuilder.setMessage("Your going to delete the Note\nTitle: \n\t\t${note.title}\nContent: \n\t\t${note.content}\n\nAre you sure you want to Continue? ")

        alertDialogBuilder.setPositiveButton("Yes"){ dialog: DialogInterface, which:Int ->
            delete(note.id)
            var temp: Int = 0
            repeat(noteList.size){
                if (noteList[it].id==note.id){
                    noteList.removeAt(it)
                    temp = it
                }
            }
            recyclerView.adapter?.notifyItemChanged(temp)

        }

        alertDialogBuilder.setNegativeButton("No"){ dialog: DialogInterface, which:Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
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