package com.example.fragmentsample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.fragmentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // text to passed from fragments
        var maintext = "This is a text from the Main Activity"

        //creates objects from fragments
        val f1 = FragmentOne()
        val f2 = FragmentTwo()
        val f3 = FragmentThree()

        //
        supportFragmentManager.beginTransaction().apply {
            var mBundle = Bundle()
            mBundle.putString("maintext",maintext)
            f1.arguments = mBundle
            replace(R.id.fragmentContainerView,f1)
            commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menuHome ->{
                    var mBundle = Bundle()
                    mBundle.putString("maintext",maintext)
                    f1.arguments = mBundle
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,f1)
                        commit()
                    }
                }
                R.id.menuProfile ->{
                    var mBundle = Bundle()
                    mBundle.putString("maintext",maintext)
                    f2.arguments = mBundle
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,f2)
                        commit()
                    }
                }
                R.id.menuNotification ->{
                    var mBundle = Bundle()
                    mBundle.putString("maintext",maintext)
                    f3.arguments = mBundle
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView,f3)
                        commit()
                    }
                }
            }
            true
        }
        binding.materialToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.appBarSetting -> {
                    showAlertDialog()
                }
            }
            true
        }


//        binding.button.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                //replace(binding.fragmentContainerView,f1)
//                replace(R.id.fragmentContainerView,f1)
//                commit() //to display fragment
//            }
//        }
//
//        binding.button2.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragmentContainerView,f2)
//                commit()
//            }
//        }



    }
    private fun showAlertDialog(){
        val alertDialogBuilder  = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Dialog Title")
        alertDialogBuilder.setMessage("Dialog Message")

        alertDialogBuilder.setPositiveButton("OK"){ dialog: DialogInterface, which: Int->

            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Cancel"){ dialog: DialogInterface, which: Int->

            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}