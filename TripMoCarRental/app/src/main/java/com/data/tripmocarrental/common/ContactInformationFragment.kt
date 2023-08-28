package com.data.tripmocarrental.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.FragmentContactInformationBinding


class ContactInformationFragment : Fragment() {
    private lateinit var binding: FragmentContactInformationBinding

    //Invoke variable
    var onNextProcess:((Int)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactInformationBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        binding.apply {
            btnNextProcess.setOnClickListener {
                onNextProcess?.invoke(0)
            }
        }


        return binding.root
    }

}