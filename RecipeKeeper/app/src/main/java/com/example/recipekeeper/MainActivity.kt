package com.example.recipekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.Toast
import com.example.recipekeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var recipe = mutableListOf<String>()
    private var ingredients = mutableListOf<String>()
    private var instruction = mutableListOf<String>()
    private var tempText:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnClear.setOnClickListener {
            Toast.makeText(applicationContext,"All fields are cleared",Toast.LENGTH_SHORT).show()
            binding.edtRecipeName.text.clear()
            binding.edtIngredients.text.clear()
            binding.edtInstructions.text.clear()
            binding.txtViewRecipeArea.text = ""

            recipe.clear()
            ingredients.clear()
            instruction.clear()
        }

        binding.btnAddRecipe.setOnClickListener {

            if (binding.edtRecipeName.text.toString()==""){
                Toast.makeText(applicationContext,"Empty Input Text",Toast.LENGTH_SHORT).show()
            }else {
                recipe.add(binding.edtRecipeName.text.toString())
                connectString()
                binding.edtRecipeName.text.clear()
            }
        }

        binding.btnAddIngredients.setOnClickListener {

            if (binding.edtIngredients.text.toString()==""){
                Toast.makeText(applicationContext,"Empty Input Text",Toast.LENGTH_SHORT).show()
            }else {
                ingredients.add(binding.edtIngredients.text.toString())
                connectString()
                binding.edtIngredients.text.clear()
            }
        }

        binding.btnAddInstructions.setOnClickListener {

            if (binding.edtInstructions.text.toString()==""){
                Toast.makeText(applicationContext,"Empty Input Text",Toast.LENGTH_SHORT).show()
            }else {
                instruction.add(binding.edtInstructions.text.toString())
                connectString()
                binding.edtInstructions.text.clear()
            }
        }

        binding.btnSaveRecipe.setOnClickListener {
            if(recipe.size>0 && ingredients.size>0 && instruction.size>0){
                Toast.makeText(applicationContext,"Recipe Saved",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Please complete all Required Information",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun connectString(){

        repeat(recipe.size){
            tempText += "${recipe.elementAt(it)}\n"
        }

        repeat(ingredients.size){
            tempText +=  "${ingredients.elementAt(it)}\n"
        }

        repeat(instruction.size){
            tempText +=  "${instruction.elementAt(it)}\n"
        }

        binding.txtViewRecipeArea.text=tempText
        tempText=""

    }
}