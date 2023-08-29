package com.data.tripmocarrental.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
//                if( binding.etHouseBlock.text!!.isEmpty() ||
//                    binding.etStreet.text!!.isEmpty() ||
//                    //binding.etBuildingSubdivision.text!!.isEmpty() || //Optional Information
//                    binding.etBarangay.text!!.isEmpty() ||
//                    binding.etCity.text!!.isEmpty() ||
//                    binding.etProvince.text!!.isEmpty() ||
//                    binding.etZipCode.text!!.isEmpty() ||
//                    binding.etCellphone.text!!.isEmpty()
//                    //binding.etTelephone.text!!.isEmpty() //Optional Information
//                ) {
//                    Toast.makeText(requireActivity(), "Filled up the empty field with necessary information", Toast.LENGTH_SHORT).show()
//                }else{
//                    onNextProcess?.invoke(0)
//                }

                //For Checking only
                onNextProcess?.invoke(0)
            }
        }


        return binding.root
    }

}