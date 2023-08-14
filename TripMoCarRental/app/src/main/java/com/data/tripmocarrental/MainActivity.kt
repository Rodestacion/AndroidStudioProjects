package com.data.tripmocarrental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.tripmocarrental.registrationborrower.BorrowerRegistration
import com.data.tripmocarrental.registrationowner.OwnerRegistration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userType = intent.getIntExtra("user",0)

        if(userType == 1){
            val nextScreen = Intent(this,BorrowerRegistration::class.java)
            startActivity(nextScreen)
        }else if(userType == 2){
            val nextScreen = Intent(this,OwnerRegistration::class.java)
            startActivity(nextScreen)
        }

    }
}

