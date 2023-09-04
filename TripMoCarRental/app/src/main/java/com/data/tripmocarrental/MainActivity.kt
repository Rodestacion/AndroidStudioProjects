package com.data.tripmocarrental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.data.tripmocarrental.databinding.ActivityMainBinding
import com.data.tripmocarrental.registrationborrower.BorrowerRegistration
import com.data.tripmocarrental.registrationowner.OwnerRegistration
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        auth = Firebase.auth

        toggle = ActionBarDrawerToggle(this,binding.root, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        var userInfo = intent.getStringArrayListExtra("userInfo")
        Toast.makeText(applicationContext, userInfo.toString(), Toast.LENGTH_SHORT).show()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationBar.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navHome->{
                    Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.navSearch->{
                    Toast.makeText(applicationContext, "Search", Toast.LENGTH_SHORT).show()
                }
                R.id.navSchedule->{
                    Toast.makeText(applicationContext, "Schedule", Toast.LENGTH_SHORT).show()
                }
                R.id.navProfile->{
                    Toast.makeText(applicationContext, "Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.navLogout->{
                    userLogout()
                }
            }
            true
        }

        binding.floatingActionButton.setOnClickListener {

        }



//        val userType = intent.getIntExtra("user",0)
//
//        if(userType == 1){
//            val nextScreen = Intent(this,BorrowerRegistration::class.java)
//            startActivity(nextScreen)
//        }else if(userType == 2){
//            val nextScreen = Intent(this,OwnerRegistration::class.java)
//            startActivity(nextScreen)
//        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun userLogout(){
        auth.signOut()
        val nextScreen = Intent(this,Login::class.java)
        startActivity(nextScreen)
        finish()
    }

}
