package com.data.tripmocarrental.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.FragmentSupportingDocumentBinding


class SupportingDocumentFragment : Fragment() {
    private lateinit var binding: FragmentSupportingDocumentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSupportingDocumentBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        return binding.root
    }
}