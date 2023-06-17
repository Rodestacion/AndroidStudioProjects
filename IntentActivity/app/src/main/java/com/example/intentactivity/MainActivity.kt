package com.example.intentactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.intentactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMainScreen.setOnClickListener {
            val myIntent = Intent(this, WelcomeActivity::class.java)
            val myInput = binding.txtMainScreen.text.toString()
            myIntent.putExtra("myName",myInput)
            startActivity(myIntent) //display main screen
            //finish() // close the main screen
        }
        Log.i("TEST","onCreate")
    }
    //Activity life cycle
    override fun onStart() {
        super.onStart()
        Log.i("TEST","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("TEST","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("TEST","onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TEST","onDestroy")
    }

}