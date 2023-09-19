package com.data.tripmocarrental.borrower.reservationfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.FragmentCarReservationBinding

class CarReservationFragment : Fragment() {
    private lateinit var binding: FragmentCarReservationBinding

    //Invoke variable
    var onClose:((Int)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarReservationBinding.inflate(layoutInflater,container,false)
        var myBundle = arguments
        val reserveInfo = myBundle?.getStringArrayList("bookInfoKey")
        val selectedVehicle = myBundle?.getStringArrayList("selectedVehicle")
        val userInfo = myBundle?.getStringArrayList("userInfo")
        val ownerInfo = myBundle?.getStringArrayList("ownerInfo")
        val licenseInfo = myBundle?.getStringArrayList("licenseInfo")


        //Initialize Display
        var finalData = ArrayList<String>()
        finalData.add(userInfo!!.elementAt(0)) //borrowerEmail
        finalData.add(userInfo.elementAt(2)) //borrowerName
        binding.etReserveBorrowerName.setText(userInfo.elementAt(2))
        finalData.add(userInfo.elementAt(5)) //borrowerAddress
        binding.etReserveBorrowerAddress.setText(userInfo.elementAt(5))
        finalData.add(userInfo.elementAt(6)) //borrowerContact
        binding.etReserveBorrowerContact.setText(userInfo.elementAt(6))
        if(licenseInfo?.elementAt(0)=="NONE"){
            finalData.add("NONE") //licenseInfo
            binding.etReserveBorrowerLicense.setText("NONE")
            finalData.add("NONE") //licenseCode
            binding.etReserveBorrowerCode.setText("NONE")
        }else{
            val data1 = "${licenseInfo?.elementAt(1)} / ${licenseInfo?.elementAt(2)}"
            finalData.add(data1) //license info
            binding.etReserveBorrowerLicense.setText(data1)
            val data2 = "${licenseInfo?.elementAt(3)} / ${licenseInfo?.elementAt(6)}"
            finalData.add(data2) //licenseCode
            binding.etReserveBorrowerCode.setText(data2)
        }
        finalData.add(ownerInfo!!.elementAt(0)) //ownerEmail
        finalData.add(ownerInfo.elementAt(2)) //ownerName
        binding.etReserveOwnerName.setText(ownerInfo.elementAt(2))
        finalData.add(ownerInfo.elementAt(5)) //ownerAddress
        binding.etReserveOwnerAddress.setText(ownerInfo.elementAt(5))
        finalData.add(ownerInfo.elementAt(6)) //ownerContact
        binding.etReserveOwnerContact.setText(ownerInfo.elementAt(6))
        finalData.add(selectedVehicle!!.elementAt(0)) //vehicleID
        val vehicle1 = "${selectedVehicle.elementAt(1)} / ${selectedVehicle.elementAt(2)} / ${selectedVehicle.elementAt(3)}"
        finalData.add(vehicle1) //vehicleName
        binding.etReserveVehicleName.setText(vehicle1)
        val vehicle2 = "${selectedVehicle.elementAt(4)} / ${selectedVehicle.elementAt(6)}"
        finalData.add(vehicle2) //vehicleSpecification
        binding.etReserveVehicleCapacity.setText(vehicle2)
        val vehicle3 = "${selectedVehicle.elementAt(7)} / ${selectedVehicle.elementAt(9)}"
        finalData.add(vehicle3) //vehicleRegistration
        binding.etReserveVehicleRegister.setText(vehicle3)
        finalData.add(reserveInfo!!.elementAt(3)) //Drive
        binding.etReserveVehicleDrive.setText(reserveInfo!!.elementAt(3))
        finalData.add(reserveInfo.elementAt(0))//date
        finalData.add(reserveInfo.elementAt(1))
        var reserveDate = "${reserveInfo.elementAt(0)} to ${reserveInfo.elementAt(1)}"
        binding.etReserveVehicleDate.setText(reserveDate)
        finalData.add(reserveInfo.elementAt(2))
        binding.etReserveVehiclePick.setText(reserveInfo.elementAt(2))
        finalData.add(reserveInfo.elementAt(4))
        binding.etReserveVehicleRent.setText(reserveInfo.elementAt(4))


        binding.btnConfirm.setOnClickListener {
            setFragmentResult("requestKey", bundleOf("bookInfoKey" to finalData))
            onClose?.invoke(0)
        }

        return binding.root
    }

}