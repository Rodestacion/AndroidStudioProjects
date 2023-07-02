package com.example.myminiapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myminiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val f1 = VocabFragment()
        val f2 = PhrasesFragment()
        val f3 = NumbersFragment()

        //default fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,f1)
            commit()
        }

        //Navigation bar
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.vocabItem -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,f1)
                        commit()
                    }
                }
                R.id.phrasesItem -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,f2)
                        commit()
                    }
                }
                R.id.numbersItem -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,f3)
                        commit()
                    }
                }
            }
            true
        }

        //Material Tool Bar
        binding.materialToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.aboutMenu->{
                    showAboutDialog()
                }
            }
            true
        }

    }

    private fun showAboutDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("About")
        alertDialogBuilder.setMessage("Developer: \n\t\tRodney Estacion\n\nContent Source: \n\t\tFluent in 3 MONTHS\n\nURL: \n\t\thttps://www.fluentin3months.com")

        alertDialogBuilder.setPositiveButton("OK"){ dialog:DialogInterface,which:Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}