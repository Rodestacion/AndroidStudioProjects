package com.data.tripmocarrental.registrationborrower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.tripmocarrental.R
import com.data.tripmocarrental.common.ProfileFragment
import com.data.tripmocarrental.common.SupportingDocumentFragment
import com.data.tripmocarrental.databinding.ActivityBorrowerRegistrationBinding

class BorrowerRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityBorrowerRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrowerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val f1 = ProfileFragment()
        val f2 = BorrowerOtherInformationFragment()
        val f3 = SupportingDocumentFragment()

        //initialize default Borrower Registration Form fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.borrowerFragmentContainerView,f1)
            commit()
        }

        //Move on next fragment
        f1.onNextProcess = {
            supportFragmentManager.beginTransaction().apply {
                binding.progressBar3.progress = 33
                replace(R.id.borrowerFragmentContainerView,f2)
                commit()
            }
        }

        f2.onNextProcess={
            supportFragmentManager.beginTransaction().apply {
                binding.progressBar3.progress = 66
                replace(R.id.borrowerFragmentContainerView,f3)
                commit()
            }
        }



    }
}