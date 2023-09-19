package com.data.tripmocarrental.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var selectedItem = intent.getStringArrayListExtra("selectedItem")

        binding.etReserveBorrowerName.setText(selectedItem?.elementAt(1))
        binding.etReserveBorrowerAddress.setText(selectedItem?.elementAt(2))
        binding.etReserveBorrowerContact.setText(selectedItem?.elementAt(3))
        binding.etReserveBorrowerLicense.setText(selectedItem?.elementAt(4))
        binding.etReserveBorrowerCode.setText(selectedItem?.elementAt(5))
        binding.etReserveOwnerName.setText(selectedItem?.elementAt(7))
        binding.etReserveOwnerAddress.setText(selectedItem?.elementAt(8))
        binding.etReserveOwnerContact.setText(selectedItem?.elementAt(9))
        binding.etReserveVehicleName.setText(selectedItem?.elementAt(11))
        binding.etReserveVehicleCapacity.setText(selectedItem?.elementAt(12))
        binding.etReserveVehicleRegister.setText(selectedItem?.elementAt(13))
        binding.etReserveVehicleDrive.setText(selectedItem?.elementAt(14))
        var reserveDate = "${selectedItem?.elementAt(15)} to ${selectedItem?.elementAt(16)}"
        binding.etReserveVehicleDate.setText(reserveDate)
        binding.etReserveVehiclePick.setText(selectedItem?.elementAt(17))
        binding.etReserveVehicleRent.setText(selectedItem?.elementAt(18))
        Log.d("VehicleID",selectedItem!!.elementAt(19))
    }
}