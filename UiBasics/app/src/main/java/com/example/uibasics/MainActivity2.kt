package com.example.uibasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


//old way of UI binding
class MainActivity2 : AppCompatActivity() {
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //findviewbyID
        button = findViewById(R.id.btnProfile)
        button.setOnClickListener {
            Toast.makeText(applicationContext,"Profile now can be edited",Toast.LENGTH_LONG).show()
        }
    }
}