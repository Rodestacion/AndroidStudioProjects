package com.data.apexercise16

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var team1Score: Int = 0
    var team2Score: Int = 0
    var winner:String = ""
    var lose:String = ""
    var lock: Boolean = false
    var gameButtonText: String = "Game Finished"
    var status: String = ""

    fun team1Scored2(){
        team1Score+=2
    }
    fun team1Scored3(){
        team1Score+=3
    }
    fun team1FreeThrow(){
        team1Score+=1
    }

    fun team2Scored2(){
        team2Score+=2
    }
    fun team2Scored3(){
        team2Score+=3
    }
    fun team2FreeThrow(){
        team2Score+=1
    }

    fun gameReset(){
        onCleared()
    }

    fun teamStatus(status:String){
        this.status = status
    }

    fun newGame(){
        lock = true
        gameButtonText = "Set New Game"
    }
    fun gameOngoing(){
        lock = false
        gameButtonText = "Game Finished"
    }


    override fun onCleared() {
        super.onCleared()
        team1Score =0
        team2Score = 0
        winner = ""
        lose = ""
    }
}