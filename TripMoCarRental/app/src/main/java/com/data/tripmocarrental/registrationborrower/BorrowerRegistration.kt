package com.data.tripmocarrental.registrationborrower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.tripmocarrental.R
import com.data.tripmocarrental.common.ContactInformationFragment
import com.data.tripmocarrental.common.ProfileFragment
import com.data.tripmocarrental.common.SplashScreen
import com.data.tripmocarrental.common.SupportingDocumentFragment
import com.data.tripmocarrental.databinding.ActivityBorrowerRegistrationBinding

class BorrowerRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityBorrowerRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrowerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val f1 = ProfileFragment()
        val f2 = ContactInformationFragment()
        val f3 = BorrowerOtherInformationFragment()
        val f4 = SupportingDocumentFragment()

        //initialize default Borrower Registration Form fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.borrowerFragmentContainerView,f1)
            commit()
        }

        //Move on next fragment
        f1.onNextProcess = {
            supportFragmentManager.beginTransaction().apply {
                binding.progressBar3.progress = 25
                replace(R.id.borrowerFragmentContainerView,f2)
                commit()
            }
        }

        f2.onNextProcess={
            supportFragmentManager.beginTransaction().apply {
                binding.progressBar3.progress = 50
                replace(R.id.borrowerFragmentContainerView,f3)
                commit()
            }
        }

        f3.onNextProcess={
            supportFragmentManager.beginTransaction().apply {
                binding.progressBar3.progress = 75
                replace(R.id.borrowerFragmentContainerView,f4)
                commit()
            }
        }

        f4.onNextProcess={
            val nextScreen = Intent(this,SplashScreen::class.java)
            startActivity(nextScreen)
            finish()
        }



    }
}