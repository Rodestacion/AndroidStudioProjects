package com.data.canvassample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import com.data.canvassample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myCanvas = MyCanvas(this)
        myCanvas.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        myCanvas.contentDescription = "This is my canvas"
        setContentView(myCanvas)
    }
}