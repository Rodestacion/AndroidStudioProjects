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
import com.data.tripmocarrental.borrower.CarReservationActivity
import com.data.tripmocarrental.common.SplashScreen
import com.data.tripmocarrental.databinding.ActivityLoginBinding
import com.data.tripmocarrental.registrationborrower.BorrowerRegistration
import com.data.tripmocarrental.registrationowner.OwnerRegistration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()


        //Sign up Textview Clickable appearance
        var newText = SpannableString (binding.txtSignUp.text.toString())
        newText.setSpan(ForegroundColorSpan(Color.BLUE),newText.length-7,newText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtSignUp.text = newText

        //SignUp TextView action on click
        binding.txtSignUp.setOnClickListener {
            val nextScreen = Intent(this,AccountRegistration::class.java)
            startActivity(nextScreen)
        }

        //Firebase Login
        binding.btnLogin.setOnClickListener {
            //Working Login Code
            binding.progressLogin.visibility = View.VISIBLE
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                binding.progressLogin.visibility = View.GONE
                Toast.makeText(applicationContext, "Input field should not empty", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        searchUserData("usertype",email)
                    } else {
                        binding.progressLogin.visibility = View.GONE
                        Log.d("LOGIN",it.exception.toString())
                        Toast.makeText(applicationContext,"Invalid Authentication",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            //End of Working Login Code


            //For Checking Main Activity UI
//            val nextScreen = Intent(this,MainActivity::class.java)
//            startActivity(nextScreen)
//            finish()

            //For Checking Main Activity UI
//            val nextScreen = Intent(this,SplashScreen::class.java)
//            startActivity(nextScreen)
//            finish()


            //For Checking CarReservationActivity UI
//            val nextScreen = Intent(this,CarReservationActivity::class.java)
//            startActivity(nextScreen)
//            finish()


        }

    }



    private fun searchUserData(collectionName:String, email:String){
        val db = FirebaseFirestore.getInstance()
        val userTypeRef = db.collection(collectionName)

        userTypeRef.whereEqualTo("email",email)
            .get()
            .addOnSuccessListener {QuerySnapshot->
                for (document in QuerySnapshot){
                    val data = document.data
                    val getEmail = data["email"]
                    val getUser = data["userType"]
                    val getProfile = data["profileComplete"]
                    val getStatus = data["profileStatus"]
                    val getID = document.id

                    //pass user info as array
                    var userArray = arrayListOf<String>()
                    userArray.add(getEmail.toString())
                    userArray.add(getUser.toString())
                    userArray.add(getProfile.toString())
                    userArray.add(getStatus.toString())
                    userArray.add(getID)

                    if(getProfile.toString().toBoolean()){
                        binding.progressLogin.visibility = View.GONE
                        val nextScreen = Intent(this, MainActivity::class.java)
                        //nextScreen.putExtra("userInfo",userArray)
                        startActivity(nextScreen)
                        finish()
                    }else{
                        if(getUser.toString() == "borrower"){
                            binding.progressLogin.visibility = View.GONE
                            val nextScreen = Intent(this, BorrowerRegistration::class.java)
                            nextScreen.putExtra("userInfo",userArray)
                            startActivity(nextScreen)
                            finish()
                        }else{
                            binding.progressLogin.visibility = View.GONE
                            val nextScreen = Intent(this, OwnerRegistration::class.java)
                            nextScreen.putExtra("userInfo",userArray)
                            startActivity(nextScreen)
                            finish()
                        }
                    }
                }

            }
            .addOnFailureListener {e->
                binding.progressLogin.visibility = View.GONE
                Toast.makeText(applicationContext, "Error: $e", Toast.LENGTH_SHORT).show()
                Log.d("FIRESTORE",e.toString())
            }


    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null)
        val currentUser = auth.currentUser

        if (currentUser!= null){
            currentUser.toString()
            searchUserData("usertype",currentUser.toString())
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        auth.signOut()
//    }
}