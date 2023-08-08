package com.data.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    //name - private, immutable
    private val _name = MutableLiveData<String>()
    val name : LiveData<String> get() =_name

    //age - private, immutable
//    private val _age = MutableLiveData<Int>()
//    val age: LiveData<Int> get() =_age
    private val _age = MutableLiveData<String>()
    val age: LiveData<String> get() =_age

    //updating
    fun updateName(name:String){
        _name.value = name
    }

//    fun updateAge(age:Int){
//        _age.value = age
//    }
    fun updateAge(age:String){
        _age.value = age
    }

    override fun onCleared(){
        super.onCleared()
    }

}