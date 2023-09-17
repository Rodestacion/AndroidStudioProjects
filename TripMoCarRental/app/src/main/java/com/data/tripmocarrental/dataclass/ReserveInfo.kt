package com.data.tripmocarrental.dataclass

data class ReserveInfo(
    val borrowerEmail:String,
    val borrowerName:String,
    val borrowerAddress:String,
    val borrowerContact:String,
    val licenseInfo:String,
    val licenseCode:String,
    val ownerEmail:String,
    val ownerName:String,
    val ownerAddress:String,
    val ownerContact:String,
    val vehicleName:String,
    val vehicleSpecification:String,
    val vehicleRegistration:String,
    val reservedStart:String,
    val reserveEnd:String,
    val reservePick:String,
    val reservedCost:String,
    val reserveStatus:String

    )