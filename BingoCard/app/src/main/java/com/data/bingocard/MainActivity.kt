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
        //Initial Background Color
        setLaneB1Background(viewModel.laneB1)
        setLaneB2Background(viewModel.laneB2)
        setLaneB3Background(viewModel.laneB3)
        setLaneB4Background(viewModel.laneB4)
        setLaneB5Background(viewModel.laneB5)

        setLaneI1Background(viewModel.laneI1)
        setLaneI2Background(viewModel.laneI2)
        setLaneI3Background(viewModel.laneI3)
        setLaneI4Background(viewModel.laneI4)
        setLaneI5Background(viewModel.laneI5)

        setLaneN1Background(viewModel.laneN1)
        setLaneN2Background(viewModel.laneN2)
        setLaneN3Background(viewModel.laneN3)
        setLaneN4Background(viewModel.laneN4)
        setLaneN5Background(viewModel.laneN5)

        setLaneG1Background(viewModel.laneG1)
        setLaneG2Background(viewModel.laneG2)
        setLaneG3Background(viewModel.laneG3)
        setLaneG4Background(viewModel.laneG4)
        setLaneG5Background(viewModel.laneG5)

        setLaneO1Background(viewModel.laneO1)
        setLaneO2Background(viewModel.laneO2)
        setLaneO3Background(viewModel.laneO3)
        setLaneO4Background(viewModel.laneO4)
        setLaneO5Background(viewModel.laneO5)

        //LaneB
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

        //LaneI
        binding.laneI1.setOnClickListener {
            if (viewModel.laneI1==0){
                viewModel.laneI1Gray()
                setLaneI1Background(viewModel.laneI1)
            }else{
                viewModel.laneI1White()
                setLaneI1Background(viewModel.laneI1)
            }
        }

        binding.laneI2.setOnClickListener {
            if (viewModel.laneI2==0){
                viewModel.laneI2Gray()
                setLaneI2Background(viewModel.laneI2)
            }else{
                viewModel.laneI2White()
                setLaneI2Background(viewModel.laneI2)
            }
        }

        binding.laneI3.setOnClickListener {
            if (viewModel.laneI3==0){
                viewModel.laneI3Gray()
                setLaneI3Background(viewModel.laneI3)
            }else{
                viewModel.laneI3White()
                setLaneI3Background(viewModel.laneI3)
            }
        }

        binding.laneI4.setOnClickListener {
            if (viewModel.laneI4==0){
                viewModel.laneI4Gray()
                setLaneI4Background(viewModel.laneI4)
            }else{
                viewModel.laneI4White()
                setLaneI4Background(viewModel.laneI4)
            }
        }

        binding.laneI5.setOnClickListener {
            if (viewModel.laneI5==0){
                viewModel.laneI5Gray()
                setLaneI5Background(viewModel.laneI5)
            }else{
                viewModel.laneI5White()
                setLaneI5Background(viewModel.laneI5)
            }
        }

        //LaneN
        binding.laneN1.setOnClickListener {
            if (viewModel.laneN1==0){
                viewModel.laneN1Gray()
                setLaneN1Background(viewModel.laneN1)
            }else{
                viewModel.laneN1White()
                setLaneN1Background(viewModel.laneN1)
            }
        }

        binding.laneN2.setOnClickListener {
            if (viewModel.laneN2==0){
                viewModel.laneN2Gray()
                setLaneN2Background(viewModel.laneN2)
            }else{
                viewModel.laneN2White()
                setLaneN2Background(viewModel.laneN2)
            }
        }

        binding.laneN3.setOnClickListener {
            if (viewModel.laneN3==0){
                viewModel.laneN3Gray()
                setLaneN3Background(viewModel.laneN3)
            }else{
                viewModel.laneN3White()
                setLaneN3Background(viewModel.laneN3)
            }
        }

        binding.laneN4.setOnClickListener {
            if (viewModel.laneN4==0){
                viewModel.laneN4Gray()
                setLaneN4Background(viewModel.laneN4)
            }else{
                viewModel.laneN4White()
                setLaneN4Background(viewModel.laneN4)
            }
        }

        binding.laneN5.setOnClickListener {
            if (viewModel.laneN5==0){
                viewModel.laneN5Gray()
                setLaneN5Background(viewModel.laneN5)
            }else{
                viewModel.laneN5White()
                setLaneN5Background(viewModel.laneN5)
            }
        }

        //LaneG
        binding.laneG1.setOnClickListener {
            if (viewModel.laneG1==0){
                viewModel.laneG1Gray()
                setLaneG1Background(viewModel.laneG1)
            }else{
                viewModel.laneG1White()
                setLaneG1Background(viewModel.laneG1)
            }
        }

        binding.laneG2.setOnClickListener {
            if (viewModel.laneG2==0){
                viewModel.laneG2Gray()
                setLaneG2Background(viewModel.laneG2)
            }else{
                viewModel.laneG2White()
                setLaneG2Background(viewModel.laneG2)
            }
        }

        binding.laneG3.setOnClickListener {
            if (viewModel.laneG3==0){
                viewModel.laneG3Gray()
                setLaneG3Background(viewModel.laneG3)
            }else{
                viewModel.laneG3White()
                setLaneG3Background(viewModel.laneG3)
            }
        }

        binding.laneG4.setOnClickListener {
            if (viewModel.laneG4==0){
                viewModel.laneG4Gray()
                setLaneG4Background(viewModel.laneG4)
            }else{
                viewModel.laneG4White()
                setLaneG4Background(viewModel.laneG4)
            }
        }

        binding.laneG5.setOnClickListener {
            if (viewModel.laneG5==0){
                viewModel.laneG5Gray()
                setLaneG5Background(viewModel.laneG5)
            }else{
                viewModel.laneG5White()
                setLaneG5Background(viewModel.laneG5)
            }
        }

        //LaneO
        binding.laneO1.setOnClickListener {
            if (viewModel.laneO1==0){
                viewModel.laneO1Gray()
                setLaneO1Background(viewModel.laneO1)
            }else{
                viewModel.laneO1White()
                setLaneO1Background(viewModel.laneO1)
            }
        }

        binding.laneO2.setOnClickListener {
            if (viewModel.laneO2==0){
                viewModel.laneO2Gray()
                setLaneO2Background(viewModel.laneO2)
            }else{
                viewModel.laneO2White()
                setLaneO2Background(viewModel.laneO2)
            }
        }

        binding.laneO3.setOnClickListener {
            if (viewModel.laneO3==0){
                viewModel.laneO3Gray()
                setLaneO3Background(viewModel.laneO3)
            }else{
                viewModel.laneO3White()
                setLaneO3Background(viewModel.laneO3)
            }
        }

        binding.laneO4.setOnClickListener {
            if (viewModel.laneO4==0){
                viewModel.laneO4Gray()
                setLaneO4Background(viewModel.laneO4)
            }else{
                viewModel.laneO4White()
                setLaneO4Background(viewModel.laneO4)
            }
        }

        binding.laneO5.setOnClickListener {
            if (viewModel.laneO5==0){
                viewModel.laneO5Gray()
                setLaneO5Background(viewModel.laneO5)
            }else{
                viewModel.laneO5White()
                setLaneO5Background(viewModel.laneO5)
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

    private fun setLaneI1Background(laneI1: Int) {
        if(laneI1 == 0){
            binding.laneI1.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneI1.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneI2Background(laneI2: Int) {
        if(laneI2 == 0){
            binding.laneI2.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneI2.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneI3Background(laneI3: Int) {
        if(laneI3 == 0){
            binding.laneI3.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneI3.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneI4Background(laneI4: Int) {
        if(laneI4 == 0){
            binding.laneI4.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneI4.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneI5Background(laneI5: Int) {
        if(laneI5 == 0){
            binding.laneI5.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneI5.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneN1Background(laneN1: Int) {
        if(laneN1 == 0){
            binding.laneN1.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneN1.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneN2Background(laneN2: Int) {
        if(laneN2 == 0){
            binding.laneN2.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneN2.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneN3Background(laneN3: Int) {
        if(laneN3 == 0){
            binding.laneN3.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneN3.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneN4Background(laneN4: Int) {
        if(laneN4 == 0){
            binding.laneN4.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneN4.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneN5Background(laneN5: Int) {
        if(laneN5 == 0){
            binding.laneN5.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneN5.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneG1Background(laneG1: Int) {
        if(laneG1 == 0){
            binding.laneG1.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneG1.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneG2Background(laneG2: Int) {
        if(laneG2 == 0){
            binding.laneG2.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneG2.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneG3Background(laneG3: Int) {
        if(laneG3 == 0){
            binding.laneG3.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneG3.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneG4Background(laneG4: Int) {
        if(laneG4 == 0){
            binding.laneG4.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneG4.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneG5Background(laneG5: Int) {
        if(laneG5 == 0){
            binding.laneG5.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneG5.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneO1Background(laneO1: Int) {
        if(laneO1 == 0){
            binding.laneO1.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneO1.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneO2Background(laneO2: Int) {
        if(laneO2 == 0){
            binding.laneO2.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneO2.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneO3Background(laneO3: Int) {
        if(laneO3 == 0){
            binding.laneO3.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneO3.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneO4Background(laneO4: Int) {
        if(laneO4 == 0){
            binding.laneO4.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneO4.setBackgroundColor(Color.GRAY)
        }
    }

    private fun setLaneO5Background(laneO5: Int) {
        if(laneO5 == 0){
            binding.laneO5.setBackgroundColor(Color.WHITE)
        }else{
            binding.laneO5.setBackgroundColor(Color.GRAY)
        }
    }

}

