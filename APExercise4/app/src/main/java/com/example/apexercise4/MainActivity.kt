package com.example.apexercise4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apexercise4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var userAndPassword = mutableMapOf<String,String>("Admin" to "12345", "User" to "1234", "Rodney" to "MD5P")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (userAndPassword.getValue(binding.txtUsername.text.toString())==binding.txtPassword.text.toString()) {
                val myIntent = Intent(this, HomePage::class.java)
                startActivity(myIntent)
                finish() // close the main screen
                Toast.makeText(applicationContext,"Welcome ${binding.txtUsername.text} to Mobile Library",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"Invalid Student ID or Password",Toast.LENGTH_SHORT).show()
            }
        }


    }
}