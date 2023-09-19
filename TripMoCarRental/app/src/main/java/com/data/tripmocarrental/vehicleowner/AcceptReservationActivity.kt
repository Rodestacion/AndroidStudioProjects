package com.data.tripmocarrental.vehicleowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.ActivityAcceptReservationBinding
import com.google.firebase.firestore.FirebaseFirestore

class AcceptReservationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAcceptReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcceptReservationBinding.inflate(layoutInflater)
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

        binding.btnAccept.setOnClickListener {
            updateReservation(selectedItem!!.elementAt(20),"Accepted")
        }

        binding.btnReject.setOnClickListener {
            updateReservation(selectedItem!!.elementAt(20),"Rejected")
        }


    }

    private fun updateReservation(reserveID:String,status:String){
        val db = FirebaseFirestore.getInstance()
        val userTypeRef = db.collection("reserveList")
        val documentRef = userTypeRef.document(reserveID)

        val update = hashMapOf<String,Any?>(
            "reserveStatus" to status
        )
        documentRef.update(update)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Reservation Status has been Changed", Toast.LENGTH_SHORT).show()
                finish()
            }
    }
}