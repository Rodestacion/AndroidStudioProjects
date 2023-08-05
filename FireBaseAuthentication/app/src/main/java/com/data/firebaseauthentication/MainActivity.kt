package com.data.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.firebaseauthentication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = Firebase.auth


        setContentView(binding.root)

        val user = auth.currentUser

        if(user == null){
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }else{
            binding.userDetails.text = user.toString()
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            auth.signOut()
            startActivity(intent)
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Firebase.auth.signOut()
    }
}