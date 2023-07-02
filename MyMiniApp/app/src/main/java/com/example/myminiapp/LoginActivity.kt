package com.example.myminiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myminiapp.databinding.ActivityLoginBinding
import java.util.Locale

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            var username = binding.editTextUserName.text.toString()
            val password = binding.editTextPassword.text.toString()
            val default = "admin"
            if(default.uppercase()==username.uppercase() && password==default){
                var nextScreen = Intent(this,MainActivity::class.java)
                startActivity(nextScreen)
                finish()
            }else{
                Toast.makeText(applicationContext, "Incorrect Username or Password!", Toast.LENGTH_SHORT).show()
                binding.editTextUserName.text?.clear()
                binding.editTextPassword.text?.clear()
            }
        }
    }
}