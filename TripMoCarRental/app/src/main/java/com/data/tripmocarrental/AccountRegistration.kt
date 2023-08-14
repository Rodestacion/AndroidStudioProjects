package com.data.tripmocarrental

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.data.tripmocarrental.databinding.ActivityAccountRegistrationBinding

class AccountRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityAccountRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Make SignIn Textview Clickable appearance
        var newText = SpannableString (binding.txtSignIn.text.toString())
        newText.setSpan(ForegroundColorSpan(Color.BLUE),newText.length-7,newText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtSignIn.text = newText

        //SignIn TextView action on click
        binding.txtSignIn.setOnClickListener {
            val nextScreen = Intent(this,Login::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val nextScreen = Intent(this,Login::class.java)
            startActivity(nextScreen)
            finish()
        }



    }
}