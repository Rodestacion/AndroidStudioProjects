package com.data.tripmocarrental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.data.tripmocarrental.borrower.BorrowerHomeFragment
import com.data.tripmocarrental.borrower.BorrowerProfileFragment
import com.data.tripmocarrental.borrower.BorrowerReservationScheduleFragment
import com.data.tripmocarrental.borrower.SearchVehicleFragment
import com.data.tripmocarrental.databinding.ActivityMainBinding
import com.data.tripmocarrental.dataclass.VehicleInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    //private lateinit var f1: SearchVehicleFragment
    //lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val f1 = BorrowerHomeFragment()
        val f2 = SearchVehicleFragment()
        val f3 = BorrowerReservationScheduleFragment()
        val f4 = BorrowerProfileFragment()

        // Initialize Firebase
        auth = Firebase.auth

        //intent pass value
        var userInfo = intent.getStringArrayListExtra("userInfo")
        Toast.makeText(applicationContext, userInfo.toString(), Toast.LENGTH_SHORT).show()

        binding.floatingActionButton.setOnClickListener {
            binding.drawerLayout.open()
        }


        binding.navigationBar.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navHome->{
                    binding.drawerLayout.close()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.borrowerMainFragmentContainerView,f1)
                        commit()
                    }
                }
                R.id.navSearch->{
                    binding.drawerLayout.close()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.borrowerMainFragmentContainerView,f2)
                        commit()
                    }
                }
                R.id.navSchedule->{
                    binding.drawerLayout.close()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.borrowerMainFragmentContainerView,f3)
                        commit()
                    }
                }
                R.id.navProfile->{
                    binding.drawerLayout.close()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.borrowerMainFragmentContainerView,f4)
                        commit()
                    }
                }
                R.id.navLogout->{
                    userLogout()
                }
            }
            true
        }


    }

    private fun userLogout(){
        auth.signOut()
        val nextScreen = Intent(this,Login::class.java)
        startActivity(nextScreen)
        finish()
    }

    //coroutine
    //https://youtu.be/DxvRcomIAQg

}
