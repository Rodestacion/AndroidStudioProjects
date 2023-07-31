package com.data.apexercise13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.data.apexercise13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var startActivityLauncher:ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher: ActivityResultLauncher<String>
    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var myProfile: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)

        var userProfile = sharedPreferences
        var profileDetail = arrayOf(userProfile.getString("image",""),
            userProfile.getString("name",""),
            userProfile.getString("location",""),
            userProfile.getString("followers","0"),
            userProfile.getString("following","0"),
            userProfile.getString("email",""),
            userProfile.getString("phone",""),
            userProfile.getString("twitter",""),
            userProfile.getString("facebook",""))
//        myProfile = profileDetail.toString() as ArrayList<String>
        Toast.makeText(applicationContext, profileDetail.toString(), Toast.LENGTH_SHORT).show()


        val f1 = ProfileFragment()
        val f2 = EditProfileFragment()


        var myBundle = Bundle()
        myBundle.putStringArray("profile",profileDetail as Array<String>)
//        myBundle.putStringArrayList("profile", myProfile)
        f1.arguments = myBundle

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerView.id,f1)
            commit()
        }

        f1.onEditProfile={
            supportFragmentManager.beginTransaction().apply {
                replace(binding.fragmentContainerView.id,f2)
                commit()
            }
            binding.btnImage.visibility = View.VISIBLE
        }

        f2.onSaveProfile = {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.fragmentContainerView.id,f1)
                commit()
            }
            binding.btnImage.visibility = View.INVISIBLE
        }

        startActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result: ActivityResult ->
            if(result.resultCode == RESULT_OK){
                val bitmap = (result.data?.extras?.get("data"))as? Bitmap ?: return@registerForActivityResult

                Glide.with(this)
                    .load(bitmap)
                    .centerCrop()
                    .circleCrop()
                    //.into(binding.imageView)
            }
        }

        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ){uri: Uri? ->
            if(uri != null){
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                //binding.imageView.setImageBitmap(bitmap)
                Glide.with(this)
                    .load(bitmap)
                    .centerCrop()
                    .circleCrop()
                    //.into(binding.imageView)
            }
        }

//        binding.imageButton.setOnClickListener {
//            showDialog()
//        }

    }

    private fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Image Selection")
        dialogBuilder.setMessage("Choose the source of your image")

        dialogBuilder.setPositiveButton("Camera"){dialog,_->
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),100)
            }else{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityLauncher.launch(intent)
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
            startActivityLauncher.launch(intent)
        }
    }
}