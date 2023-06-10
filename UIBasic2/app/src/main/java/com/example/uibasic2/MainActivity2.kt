package com.example.uibasic2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.uibasic2.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var  binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            showDialog()
        }

        binding.imageButton.setOnClickListener{
            showDialog()
        }


    }

    private fun showDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("this is a dialog box")
        alertDialogBuilder.setMessage("This is a Dialog box!")
        alertDialogBuilder.setPositiveButton("OK"){_:DialogInterface, _:Int ->  // "_" indicate that parameter is not used
            Toast.makeText(applicationContext,"Ok is Clicked", Toast.LENGTH_SHORT).show()
        }

        alertDialogBuilder.setNegativeButton("Cancelled"){_:DialogInterface, _:Int ->  // "_" indicate that parameter is not used
            Toast.makeText(applicationContext,"Cancelled is Clicked", Toast.LENGTH_SHORT).show()
        }

        alertDialogBuilder.create().show()


    }
}