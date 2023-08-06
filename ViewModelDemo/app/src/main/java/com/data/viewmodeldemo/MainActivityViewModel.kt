package com.data.viewmodeldemo

import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    var myNumber : Int = 0

    fun addNumber(){
        myNumber++

    }

    override fun onCleared() {
        super.onCleared()
    }
}