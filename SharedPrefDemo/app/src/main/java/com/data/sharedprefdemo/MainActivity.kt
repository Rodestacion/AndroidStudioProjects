package com.data.sharedprefdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.data.sharedprefdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("MyPreference", MODE_PRIVATE)

        //create shared pref
        //create()

        //get shared pref
        val userName = sharedPreferences.getString("username","no username exists")
        binding.txtResult.text = userName

        //delete
//        val editor = sharedPreferences.edit()
//        editor.remove("username")
//        editor.apply()


    }

    private fun create(){
        val editor = sharedPreferences.edit()
        editor.putString("username","PeterParker0712")
        editor.apply()
        Toast.makeText(applicationContext, "Shared preferences Created", Toast.LENGTH_SHORT).show()
    }
}