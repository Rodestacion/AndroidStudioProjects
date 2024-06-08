package com.data.icebreakinggame

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.data.icebreakinggame.databinding.ActivityMainBinding
import com.data.icebreakinggame.databinding.BreakingIceSpinnerLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Startup Display
        startupDisplay()

        binding.breakIceBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Breaking Ice Spinner Selecting", Toast.LENGTH_SHORT).show()
            randomIce()
        }

        binding.materialToolbar.setOnMenuItemClickListener{selectItem->
            when(selectItem.itemId){
                R.id.spinnerMenuBar-> {
                    showSpinnerDialog()
                }

                R.id.aboutMenuBar->{
                    showAboutDialog()
                }
            }
            true
        }

    }

    private fun showSpinnerDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this,R.style.MyDialogTheme)
        alertDialogBuilder.setTitle("Spinner Image Definition")

        val dialogLayout = layoutInflater.inflate(R.layout.breaking_ice_spinner_layout,null)
        alertDialogBuilder.setIcon(R.drawable.penguin_ice_break_icon)
        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("OK"){ dialog: DialogInterface, _:Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showAboutDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this,R.style.MyDialogTheme)
        alertDialogBuilder.setTitle("About")
        alertDialogBuilder.setIcon(R.drawable.penguin_ice_break_icon)
        alertDialogBuilder.setMessage("Developer: \n\t\tRodney Estacion\n\nPurpose: \n" +
                "\t\tThis apps serve as an electronic spinner for the game Penguin Breaking Ice or any other similar toy.\n" +
                "\nPenguin Image Source: \n\t\t* Dribble\n" +
                "\t\t" +
                "* creazilla\n\nImage Source URL: \n\t\thttps://dribbble.com\n" +
                "\t\thttps://creazilla.com")

        alertDialogBuilder.setPositiveButton("OK"){ dialog: DialogInterface, _:Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun randomIce() {
        val number = IceNumber(8)
        val breakIce = number.select()

        binding.iceImage.visibility = View.INVISIBLE
        binding.breakIceBtn.visibility = View.INVISIBLE
        binding.penguinImage.visibility = View.VISIBLE

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            when (breakIce) {
                1 -> binding.iceImage.setImageResource(R.drawable.ice_break_1)
                2 -> binding.iceImage.setImageResource(R.drawable.ice_break_2)
                3 -> binding.iceImage.setImageResource(R.drawable.ice_break_3)
                4 -> binding.iceImage.setImageResource(R.drawable.ice_break_4)
                5 -> binding.iceImage.setImageResource(R.drawable.ice_break_5)
                6 -> binding.iceImage.setImageResource(R.drawable.ice_break_6)
                7 -> binding.iceImage.setImageResource(R.drawable.ice_break_7)
                8 -> binding.iceImage.setImageResource(R.drawable.ice_break_8)
            }
            binding.iceImage.visibility = View.VISIBLE
            binding.breakIceBtn.visibility = View.VISIBLE
            binding.penguinImage.visibility = View.INVISIBLE
        }, 1500)

    }

    private fun startupDisplay(){
        binding.iceImage.visibility = View.INVISIBLE
        binding.breakIceBtn.visibility = View.INVISIBLE
        binding.penguinImage.visibility = View.VISIBLE

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            binding.iceImage.visibility = View.VISIBLE
            binding.breakIceBtn.visibility = View.VISIBLE
            binding.penguinImage.visibility = View.INVISIBLE
        }, 5000)
    }
}