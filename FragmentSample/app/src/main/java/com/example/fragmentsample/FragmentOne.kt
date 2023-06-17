package com.example.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragmentsample.databinding.FragmentOneBinding


class FragmentOne : Fragment() {
    private lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        var myBundle = arguments
        val mainText = myBundle?.getString("maintext")
        Toast.makeText(context, "Fragment1 $mainText", Toast.LENGTH_SHORT).show()

        return binding.root
    }
}