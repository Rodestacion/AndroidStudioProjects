package com.data.tripmocarrental.common

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
import com.data.tripmocarrental.R
import com.data.tripmocarrental.databinding.FragmentSupportingIdBinding


class SupportingIdFragment : Fragment() {
    private lateinit var binding: FragmentSupportingIdBinding
    private lateinit var startActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher: ActivityResultLauncher<String>
    private var imageUri: Uri?= null

    //Invoke variable
    var onNextProcess:((Int)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSupportingIdBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        //Image for ID
        startActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result: ActivityResult ->
            if(result.resultCode == AppCompatActivity.RESULT_OK){
                val bitmap = (result.data?.extras?.get("data"))as? Bitmap ?: return@registerForActivityResult

                Glide.with(this)
                    .load(bitmap)
                    .into(binding.imageID)
            }
        }

        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ){uri: Uri? ->
            if(uri != null){
                imageUri = uri
                var contentResolver = requireActivity().contentResolver
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                binding.imageID.setImageBitmap(bitmap)
            }else{
                imageUri = null
            }
        }

        binding.imgBtnID.setOnClickListener(){
            showDialog()
        }

        binding.apply {
            btnNextProcess.setOnClickListener {
                if(imageUri==null){
                    Toast.makeText(requireContext(), "Attach document must not empty", Toast.LENGTH_SHORT).show()
                }else{
                    var docLink = imageUri.toString()
                    setFragmentResult("requestKey", bundleOf("IDKey" to docLink))
                    onNextProcess?.invoke(3)
                }
            }
        }

        return binding.root
    }

    private fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this.requireActivity())
        dialogBuilder.setTitle("Image Selection")
        dialogBuilder.setMessage("Choose the source of your image")

        dialogBuilder.setPositiveButton("Camera"){dialog,_->
            if(ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.CAMERA),100)
            }else{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityLauncher.launch(intent)
            }

            dialog.dismiss()
        }

        dialogBuilder.setNegativeButton("Gallery"){ _, _->
            galleryLauncher.launch("image/*")

        }


        val dialog = dialogBuilder.create()
        dialog.show()
    }
}