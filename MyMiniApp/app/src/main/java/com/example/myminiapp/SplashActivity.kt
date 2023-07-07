package com.example.myminiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myminiapp.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.droidsonroids.gif.GifImageView

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    private  val SPLASH_DELAY : Long = 3000 // in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_DELAY)
            var myIntent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
}