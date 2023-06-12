package com.example.apexercise9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apexercise9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.subjectRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        val subject = listOf(SubjectDetail(getImageID("image1"),"Engineering Chemistry","MWF 8:00 ~ 9:30","Michael Dyson"),
            SubjectDetail(getImageID("image3"),"Autocad Drawing","MWF 9:30 ~ 11:30","Shawn Farrow"),
            SubjectDetail(getImageID("image5"),"Robotics Engineering","MWF 12:30 ~ 2:00","James Russell"),
            SubjectDetail(getImageID("image7"),"Analytic Geometry","MWF 2:30 ~ 4:00","Emily Shovelton"),
            SubjectDetail(getImageID("image9"),"Literature","MWF 4:00 ~ 5:30","Emma Goldsmith"),
            SubjectDetail(getImageID("image2"),"Taxation","TThS 8:00 ~ 9:30","Aaron Rainforth"),
            SubjectDetail(getImageID("image4"),"Physical Education","TThS 9:30 ~ 11:30","Daniel Sweeney"),
            SubjectDetail(getImageID("image6"),"Artificial Intelligence","TThS 12:30 ~ 2:00","Chloe Marshall"),
            SubjectDetail(getImageID("image8"),"Microelectronics","TThS 2:30 ~ 4:00","Lauren Isley"),
            SubjectDetail(getImageID("image10"),"Software Engineering","TThS 4:00 ~ 5:30","Jasmine Devall"))

        recyclerView.adapter = SubjectDetailAdapter(subject)

    }

    private fun getImageID(imageName: String):Int{
        return resources.getIdentifier(imageName,"drawable",packageName)
    }


}