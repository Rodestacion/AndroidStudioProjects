package com.example.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragmentsample.databinding.FragmentThreeBinding

class FragmentThree : Fragment() {
    private lateinit var binding: FragmentThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(layoutInflater,container,false)
        var myBundle = arguments
        val mainText = myBundle?.getString("maintext")
        Toast.makeText(context, "Fragment1 $mainText", Toast.LENGTH_SHORT).show()

        return binding.root

    }
}