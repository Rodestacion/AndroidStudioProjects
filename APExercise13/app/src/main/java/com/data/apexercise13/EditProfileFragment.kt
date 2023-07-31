package com.data.apexercise13

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.data.apexercise13.databinding.FragmentEditProfileBinding


class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    var onSaveProfile:((Int)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(layoutInflater,container,false)

        binding.apply {
            btnSaveProfile.setOnClickListener {
                onSaveProfile?.invoke(0)
            }
        }


        return binding.root
    }

}