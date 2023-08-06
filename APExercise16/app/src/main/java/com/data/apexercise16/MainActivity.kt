package com.data.apexercise16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.data.apexercise16.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val viewModel = ViewModelProvider(this )[MainActivityViewModel::class.java]
        binding.txtTeam1Point.text = viewModel.team1Score.toString()
        binding.txtTeam2Point.text = viewModel.team2Score.toString()
        binding.btnGameFinished.text = viewModel.gameButtonText
        binding.txtGameStatus.text =viewModel.status



        //Team 1 Score
        binding.btnTeam1TwoPoints.setOnClickListener{
            viewModel.team1Scored2()
            binding.txtTeam1Point.text = viewModel.team1Score.toString()
        }
        binding.btnTeam1ThreePoints.setOnClickListener{
            viewModel.team1Scored3()
            binding.txtTeam1Point.text = viewModel.team1Score.toString()
        }
        binding.btnTeam1FreeThrow.setOnClickListener{
            viewModel.team1FreeThrow()
            binding.txtTeam1Point.text = viewModel.team1Score.toString()
        }

        //Team 2 Score

        binding.btnTeam2TwoPoints.setOnClickListener{
            viewModel.team2Scored2()
            binding.txtTeam2Point.text = viewModel.team2Score.toString()
        }
        binding.btnTeam2ThreePoints.setOnClickListener{
            viewModel.team2Scored3()
            binding.txtTeam2Point.text = viewModel.team2Score.toString()
        }
        binding.btnTeam2FreeThrow.setOnClickListener{
            viewModel.team2FreeThrow()
            binding.txtTeam2Point.text = viewModel.team2Score.toString()
        }

        //Game Winner and Loss check
        binding.btnGameFinished.setOnClickListener {
            if(binding.btnGameFinished.text == "Game Finished"){
                viewModel.newGame()
                val team1Score = viewModel.team1Score
                val team2Score = viewModel.team2Score
                var status:String = ""

                if(team1Score>team2Score){
                    status = "Winner: Team 1 ($team1Score points)\nLose: Team 2 ($team2Score points)"
                    binding.txtGameStatus.text = status
                }else if(team2Score>team1Score){
                    status = "Winner: Team 2 ($team2Score points)\nLose: Team 1 ($team1Score points)"
                    binding.txtGameStatus.text = status
                }else{
                    status = "Draw: Team 1 &\nTeam 2 ($team1Score points)"
                    binding.txtGameStatus.text = status

                }
                viewModel.teamStatus(status)


                binding.btnGameFinished.text = viewModel.gameButtonText
                buttonLocked()

            }else{
                viewModel.gameOngoing()
                binding.btnGameFinished.text = viewModel.gameButtonText
                binding.txtTeam1Point.text = "0"
                binding.txtTeam2Point.text = "0"
                binding.txtGameStatus.text = ""
                viewModel.teamStatus("")
                buttonUnlocked()
                Toast.makeText(applicationContext, "Score Board has been reset", Toast.LENGTH_SHORT).show()
                viewModel.gameReset()
            }


        }



    }

    override fun onStart() {
        super.onStart()
        val viewModel = ViewModelProvider(this )[MainActivityViewModel::class.java]
        if(viewModel.lock){
            buttonLocked()
        }
    }

    private fun buttonLocked(){
        binding.btnTeam1TwoPoints.isClickable = false
        binding.btnTeam1ThreePoints.isClickable = false
        binding.btnTeam1FreeThrow.isClickable = false
        binding.btnTeam2TwoPoints.isClickable = false
        binding.btnTeam2ThreePoints.isClickable = false
        binding.btnTeam2FreeThrow.isClickable = false
    }

    private fun buttonUnlocked(){
        binding.btnTeam1TwoPoints.isClickable = true
        binding.btnTeam1ThreePoints.isClickable = true
        binding.btnTeam1FreeThrow.isClickable = true
        binding.btnTeam2TwoPoints.isClickable = true
        binding.btnTeam2ThreePoints.isClickable = true
        binding.btnTeam2FreeThrow.isClickable = true
    }
}