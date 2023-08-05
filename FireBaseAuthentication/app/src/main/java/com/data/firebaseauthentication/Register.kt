package com.data.firebaseauthentication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.data.firebaseauthentication.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        var newText = SpannableString (binding.txtSignIn.text.toString())
        newText.setSpan(ForegroundColorSpan(Color.BLUE),newText.length-7,newText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtSignIn.text = newText

        binding.txtSignIn.setOnClickListener {
            val nextScreen = Intent(this,Login::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.btnRegister.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val retypePassword = binding.etRePassword.text.toString()
            if(email.isEmpty()||password.isEmpty()||retypePassword.isEmpty()){
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "Input fields should not empty", Toast.LENGTH_SHORT).show()
            }else{
                if (password==retypePassword){
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_SHORT).show()
                            val nextScreen = Intent(this,Login::class.java)
                            startActivity(nextScreen)
                            binding.progressBar.visibility = View.GONE
                            auth.signOut()
                            finish()
                        }else{
                            binding.progressBar.visibility = View.GONE
                            Log.d("AUTHENTICATION",it.exception.toString())
                            if(it.exception.toString() == "com.google.firebase.auth.FirebaseAuthWeakPasswordException: The given password is invalid. [ Password should be at least 6 characters ]"){
                                Toast.makeText(applicationContext, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                }else{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Password Mismatch", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        auth.signOut()
    }

    override fun onResume() {
        super.onResume()
        auth.signOut()
    }
}