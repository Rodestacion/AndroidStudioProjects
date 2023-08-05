package com.data.firebaseauthentication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.data.firebaseauthentication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth:FirebaseAuth
    //var updateUI:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        var newText = SpannableString (binding.txtSignUp.text.toString())
        newText.setSpan(ForegroundColorSpan(Color.BLUE),newText.length-7,newText.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtSignUp.text = newText


        binding.txtSignUp.setOnClickListener {
            val nextScreen = Intent(this, Register::class.java)
            startActivity(nextScreen)
        }

        binding.btnLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "Input field should not empty", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        val nextScreen = Intent(this, MainActivity::class.java)
                        startActivity(nextScreen)
                        finish()
                    } else {
                        binding.progressBar.visibility = View.GONE
                        Log.d("LOGIN",it.exception.toString())
                        Toast.makeText(applicationContext,"Invalid Authentication",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        auth.signOut()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null)
        val currentUser = auth.currentUser

        //Toast.makeText(applicationContext, currentUser.toString(), Toast.LENGTH_SHORT).show()
        if (currentUser!= null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        auth.signOut()
    }
}