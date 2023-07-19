package com.data.roommultipletables.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customers (
    @PrimaryKey(autoGenerate = true) var cID:Int = 0,
    var cName:String,
    var cDetails:String
)