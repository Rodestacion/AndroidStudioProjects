package com.data.tripmocarrental

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
import com.data.tripmocarrental.databinding.ActivityAccountRegistrationBinding
import com.data.tripmocarrental.dataclass.UserType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore

class AccountRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityAccountRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Firebase Initialization
        firestore = FirebaseFirestore.getInstance()
        auth = Firebase.auth

        //Make SignIn Textview Clickable appearance
        var newText = SpannableString (binding.txtSignIn.text.toString())
        newText.setSpan(ForegroundColorSpan(Color.BLUE),newText.length-7,newText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtSignIn.text = newText

        //SignIn TextView action on click
        binding.txtSignIn.setOnClickListener {
            val nextScreen = Intent(this,Login::class.java)
            startActivity(nextScreen)
            finish()
        }

        //Firebase Registration
        binding.btnRegister.setOnClickListener {
            if(binding.btnRadBorrower.isChecked){
                userRegistration("borrower")
            }else if(binding.btnRadOwner.isChecked){
                userRegistration("owner")
            }else{
                Toast.makeText(applicationContext, "No Type of account has been selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun userRegistration(userType:String){
        binding.progressRegistration.visibility = View.VISIBLE
        binding.btnRegister.isEnabled = false
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val retypePassword = binding.etRePassword.text.toString()
        if(email.isEmpty()||password.isEmpty()||retypePassword.isEmpty()){
            binding.progressRegistration.visibility = View.GONE
            Toast.makeText(applicationContext, "Input fields should not empty", Toast.LENGTH_SHORT).show()
        }else{
            if (password==retypePassword){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        binding.progressRegistration.visibility = View.GONE
                        //auth.signOut()

                        //Restart firestore to be able to update user type
                        firestore = FirebaseFirestore.getInstance()
                        val user = UserType(email,userType,false,"first")

                        Firebase.firestore.collection("usertype")
                            .add(user)
                            .addOnSuccessListener {
                                binding.btnRegister.isEnabled = true
                                Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_SHORT).show()
                                val nextScreen = Intent(this,Login::class.java)
                                startActivity(nextScreen)
                                finish()

                            }
                            .addOnFailureListener {e->
                                binding.btnRegister.isEnabled = true
                                Toast.makeText(applicationContext, "Registration Failed", Toast.LENGTH_SHORT).show()
                            }

                    }else{
                        binding.progressRegistration.visibility = View.GONE
                        Log.d("AUTHENTICATION",it.exception.toString())
                        if(it.exception.toString() == "com.google.firebase.auth.FirebaseAuthWeakPasswordException: The given password is invalid. [ Password should be at least 6 characters ]"){
                            binding.btnRegister.isEnabled = true
                            Toast.makeText(applicationContext, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }else{
                binding.progressRegistration.visibility = View.GONE
                binding.btnRegister.isEnabled = true
                Toast.makeText(applicationContext, "Password Mismatch", Toast.LENGTH_SHORT).show()
            }
        }
    }
}