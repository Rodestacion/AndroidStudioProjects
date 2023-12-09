package com.data.bingocard

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.data.bingocard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setLaneB1Background(viewModel.laneB1)

        binding.laneB1.setOnClickListener {
            if (viewModel.laneB1==0){
                viewModel.laneB1Gray()
                setLaneB1Background(viewModel.laneB1)
            }else{
                viewModel.laneB1White()
                setLaneB1Background(viewModel.laneB1)
            }
        }

        binding.laneB2.setOnClickListener {
            if (viewModel.laneB2==0){
                viewModel.laneB2Gray()
                setLaneB2Background(viewModel.laneB2)
            }else{
                viewModel.laneB2White()
                setLaneB2Background(viewModel.laneB2)
            }
        }

        binding.laneB3.setOnClickListener {
            if (viewModel.laneB3==0){
                viewModel.laneB3Gray()
                setLaneB3Background(viewModel.laneB3)
            }else{
                viewModel.laneB3White()
                setLaneB3Background(viewModel.laneB3)
            }
        }

        binding.laneB4.setOnClickListener {
            if (viewModel.laneB4==0){
                viewModel.laneB4Gray()
                setLaneB4Background(viewModel.laneB4)
            }else{
                viewModel.laneB4White()
                setLaneB4Background(viewModel.laneB4)
            }
        }

        binding.laneB5.setOnClickListener {
            if (viewModel.laneB5==0){
                viewModel.laneB5Gray()
                setLaneB5Background(viewModel.laneB5)
            }else{
                viewModel.laneB5White()
                setLaneB5Background(viewModel.laneB5)
            }
        }
    }

    private fun setLaneB1Background(laneB1: Int) {
        if(laneB1 == 0){
            binding.laneB1.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneB1.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneB2Background(laneB2: Int) {
        if(laneB2 == 0){
            binding.laneB2.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneB2.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneB3Background(laneB3: Int) {
        if(laneB3 == 0){
            binding.laneB3.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneB3.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneB4Background(laneB4: Int) {
        if(laneB4 == 0){
            binding.laneB4.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneB4.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneB5Background(laneB5: Int) {
        if(laneB5 == 0){
            binding.laneB5.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneB5.setBackgroundColor(Color.GRAY)
        }
    }

}

