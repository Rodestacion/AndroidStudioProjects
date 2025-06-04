package com.data.icebreakinggame

class IceNumber(private val numbers:Int){
    fun select():Int{
        return (1..numbers).random()
    }
}