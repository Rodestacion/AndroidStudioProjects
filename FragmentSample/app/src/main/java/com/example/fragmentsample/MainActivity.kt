package com.example.fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //creates objects from fragments
        val f1 = FragmentOne()
        val f2 = FragmentTwo()

        binding.button.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                //replace(binding.fragmentContainerView,f1)
                replace(R.id.fragmentContainerView,f1)
                commit() //to display fragment
            }
        }

        binding.button2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView,f2)
                commit()
            }
        }



    }
}