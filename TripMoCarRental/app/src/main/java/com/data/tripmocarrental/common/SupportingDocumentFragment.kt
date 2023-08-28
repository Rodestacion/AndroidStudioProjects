package com.data.tripmocarrental.common

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.data.tripmocarrental.databinding.FragmentSupportingDocumentBinding


class SupportingDocumentFragment : Fragment() {
    private lateinit var binding: FragmentSupportingDocumentBinding
    private lateinit var startActivityLauncher1: ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher1: ActivityResultLauncher<String>
    private lateinit var startActivityLauncher2: ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher2: ActivityResultLauncher<String>

    //Invoke variable
    var onNextProcess:((Int)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSupportingDocumentBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment


        //Image for ID
        startActivityLauncher1 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result: ActivityResult ->
            if(result.resultCode == AppCompatActivity.RESULT_OK){
                val bitmap = (result.data?.extras?.get("data"))as? Bitmap ?: return@registerForActivityResult

                Glide.with(this)
                    .load(bitmap)
                    .into(binding.imageID)
                //binding.imageView.setImageBitmap(bitmap)
            }
        }

        galleryLauncher1 = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ){uri: Uri? ->
            if(uri != null){
                var contentResolver = requireActivity().contentResolver
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                binding.imageID.setImageBitmap(bitmap)
            }
        }

        binding.imgBtnID.setOnClickListener(){
            showDialog1()
        }

        //Image for Address
        startActivityLauncher2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result: ActivityResult ->
            if(result.resultCode == AppCompatActivity.RESULT_OK){
                val bitmap = (result.data?.extras?.get("data"))as? Bitmap ?: return@registerForActivityResult

                Glide.with(this)
                    .load(bitmap)
                    .optionalCenterCrop()
                    .into(binding.imageAddress)
            }
        }

        galleryLauncher2 = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ){uri: Uri? ->
            if(uri != null){
                var contentResolver = requireActivity().contentResolver
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                binding.imageAddress.setImageBitmap(bitmap)
            }
        }

        binding.imgBtnAddress.setOnClickListener(){
            showDialog2()
        }

        binding.apply {
            btnNextProcess.setOnClickListener {
                onNextProcess?.invoke(0)
            }
        }

        return binding.root
    }

    private fun showDialog1() {
        val dialogBuilder = AlertDialog.Builder(this.requireActivity())
        dialogBuilder.setTitle("Image Selection")
        dialogBuilder.setMessage("Choose the source of your image")

        dialogBuilder.setPositiveButton("Camera"){dialog,_->
            if(ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.CAMERA),100)
            }else{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityLauncher1.launch(intent)
            }

            dialog.dismiss()
        }

        dialogBuilder.setNegativeButton("Gallery"){dialog,_->
            galleryLauncher1.launch("image/*")

        }


        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun showDialog2() {
        val dialogBuilder = AlertDialog.Builder(this.requireActivity())
        dialogBuilder.setTitle("Image Selection")
        dialogBuilder.setMessage("Choose the source of your image")

        dialogBuilder.setPositiveButton("Camera"){dialog,_->
            if(ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.CAMERA),100)
            }else{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityLauncher2.launch(intent)
            }

            dialog.dismiss()
        }

        dialogBuilder.setNegativeButton("Gallery"){dialog,_->
            galleryLauncher2.launch("image/*")

        }


        val dialog = dialogBuilder.create()
        dialog.show()
    }
}