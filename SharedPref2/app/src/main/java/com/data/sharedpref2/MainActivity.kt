package com.data.sharedpref2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.data.sharedpref2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        //check if first time to load
        var firstTime = sharedPreferences.getBoolean("firstTime",true)

        if(firstTime){
            val editor =sharedPreferences.edit()
            editor.putBoolean("firstTime",false)
            editor.apply()
            //go to screen
            val intent = Intent(this,WelcomeActivity::class.java)
            startActivity(intent)
            finish()

        }else{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()


        }

        //deletePref()



    }

    private fun deletePref(){
        val editor =sharedPreferences.edit()
        editor.remove("firstTime")
        editor.apply()
    }
}