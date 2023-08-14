package com.data.tripmocarrental

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import com.data.tripmocarrental.databinding.ActivityLoginBinding
import com.data.tripmocarrental.registrationborrower.BorrowerRegistration
import com.data.tripmocarrental.registrationowner.OwnerRegistration

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Sign up Textview Clickable appearance
        var newText = SpannableString (binding.txtSignUp.text.toString())
        newText.setSpan(ForegroundColorSpan(Color.BLUE),newText.length-7,newText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtSignUp.text = newText

        //SignUp TextView action on click
        binding.txtSignUp.setOnClickListener {
            val nextScreen = Intent(this,AccountRegistration::class.java)
            startActivity(nextScreen)
        }

        //Login
        binding.btnLogin.setOnClickListener {
            val username = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (username == "user" && password == "user123"){
                val nextScreen = Intent(this,MainActivity::class.java)
                nextScreen.putExtra("user",1)
                startActivity(nextScreen)
                finish()

            }else if (username == "admin" && password == "admin123") {
                val nextScreen = Intent(this, MainActivity::class.java)
                nextScreen.putExtra("user",2)
                startActivity(nextScreen)
                finish()
            }else{
                Toast.makeText(applicationContext, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }


        }


    }
}