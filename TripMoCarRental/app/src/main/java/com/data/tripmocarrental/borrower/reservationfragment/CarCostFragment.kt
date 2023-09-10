package com.data.tripmocarrental.borrower.reservationfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.FragmentCarCostBinding


class CarCostFragment : Fragment() {
    private lateinit var binding: FragmentCarCostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarCostBinding.inflate(layoutInflater,container,false)


        return binding.root
    }
}