package com.data.mvmmsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.data.mvmmsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

        var viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.mainViewModel = viewModel

        viewModel.loginResult.observe(this, Observer{ success ->
            if (success) {
                Toast.makeText(applicationContext, "UserLogged In!", Toast.LENGTH_SHORT).show()
                val nextScreen = Intent(this,HomeScreen::class.java)
                val username = binding.username.text.toString()
                nextScreen.putExtra("uname",username)
                startActivity(nextScreen)
                finish()
            } else {
                Toast.makeText(applicationContext, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnLogin.setOnClickListener {
            var username = binding.username.text.toString()
            var password = binding.password.text.toString()
            viewModel.performLogin(username,password)
            //Toast.makeText(applicationContext, "$username $password", Toast.LENGTH_SHORT).show()
        }


    }

}