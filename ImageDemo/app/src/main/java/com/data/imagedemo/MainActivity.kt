package com.data.imagedemo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.data.imagedemo.databinding.ActivityMainBinding
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var starActivityLauncher:ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        starActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result:ActivityResult ->
            if(result.resultCode == RESULT_OK){
                val bitmap = (result.data?.extras?.get("data"))as? Bitmap ?: return@registerForActivityResult

                Glide.with(this)
                    .load(bitmap)
                    .centerCrop()
                    .circleCrop()
                    .into(binding.imageView)
                //binding.imageView.setImageBitmap(bitmap)
            }
        }

        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ){uri:Uri? ->
            if(uri != null){
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                binding.imageView.setImageBitmap(bitmap)
            }
        }

        binding.imageButton.setOnClickListener {
            showDialog()
        }



    }

    private fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Image Selection")
        dialogBuilder.setMessage("Choose the source of your image")

        dialogBuilder.setPositiveButton("Camera"){dialog,_->
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),100)
            }else{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                starActivityLauncher.launch(intent)
            }

            dialog.dismiss()
        }

        dialogBuilder.setNegativeButton("Gallery"){dialog,_->
            galleryLauncher.launch("image/*")

        }


        val dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100 && grantResults.isNotEmpty()&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            starActivityLauncher.launch(intent)
        }
    }
}