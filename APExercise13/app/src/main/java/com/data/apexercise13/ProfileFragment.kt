package com.data.apexercise13

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.data.apexercise13.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding


    var onEditProfile:((Int)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)

        var myArguments = arguments
        var mainArray = myArguments?.getStringArrayList("profile")

        Toast.makeText(context, mainArray.toString(), Toast.LENGTH_SHORT).show()

        binding.apply {
            btnEditProfile.setOnClickListener {
                onEditProfile?.invoke(0)
            }
        }


        return binding.root
    }
}