package com.example.diceroller

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pl.droidsonroids.gif.GifImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener {
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
            toast.show()
            rollDice()
        }

        // Do a dice roll when the app starts
        rollDice()
    }

    private fun rollDice() {
        val rollButton: Button = findViewById(R.id.button)
        val dice = Dice(6)
        val  diceRoll = dice.roll()

        var diceImage: ImageView = findViewById(R.id.imageView)
        var onRollDice: GifImageView = findViewById(R.id.gifImage)

        //onRollDice.setImageResource(R.drawable.dice)
        rollButton.setVisibility(View.INVISIBLE)
        diceImage.setVisibility(View.INVISIBLE)
        onRollDice.setVisibility(View.VISIBLE)


        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            when (diceRoll) {
                1 -> diceImage.setImageResource(R.drawable.dice_1)
                2 -> diceImage.setImageResource(R.drawable.dice_2)
                3 -> diceImage.setImageResource(R.drawable.dice_3)
                4 -> diceImage.setImageResource(R.drawable.dice_4)
                5 -> diceImage.setImageResource(R.drawable.dice_5)
                6 -> diceImage.setImageResource(R.drawable.dice_6)
            }

            onRollDice.setVisibility(View.INVISIBLE)
            diceImage.setVisibility(View.VISIBLE)
            rollButton.setVisibility(View.VISIBLE)
        }, 1500)

    }

}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}