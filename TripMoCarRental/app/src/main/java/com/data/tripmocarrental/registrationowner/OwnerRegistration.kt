package com.data.tripmocarrental.registrationowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.tripmocarrental.R
import com.data.tripmocarrental.common.ContactInformationFragment
import com.data.tripmocarrental.common.ProfileFragment
import com.data.tripmocarrental.common.SupportingDocumentFragment
import com.data.tripmocarrental.databinding.ActivityOwnerRegistrationBinding

class OwnerRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityOwnerRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val f1 = ProfileFragment()
        val f2 = ContactInformationFragment()
        val f3 = OwnerVehicleInformationFragment()
        val f4 = SupportingDocumentFragment()

        //initialize default Owner Registration Form fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.ownerFragmentContainerView,f1)
            commit()
        }

        //Move on next fragment
        f1.onNextProcess = {

            supportFragmentManager.beginTransaction().apply {
                binding.progressBar2.progress = 25
                replace(R.id.ownerFragmentContainerView,f2)
                commit()
            }
        }

        f2.onNextProcess = {
            supportFragmentManager.beginTransaction().apply {
                binding.progressBar2.progress = 50
                replace(R.id.ownerFragmentContainerView,f3)
                commit()
            }
        }

        f3.onNextProcess = {
            supportFragmentManager.beginTransaction().apply {
                binding.progressBar2.progress = 75
                replace(R.id.ownerFragmentContainerView,f4)
                commit()
            }
        }

    }
}