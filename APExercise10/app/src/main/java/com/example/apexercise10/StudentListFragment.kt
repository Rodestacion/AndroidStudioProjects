package com.example.apexercise10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apexercise10.databinding.FragmentStudentListBinding


class StudentListFragment : Fragment() {
    private lateinit var binding: FragmentStudentListBinding
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(layoutInflater,container,false)


        recyclerView = binding.studentRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)


        var student = listOf(StudentDetail(getImageID("image1"),"Crisanto Amor","9","II - Malakas","Salinas III, Bacoor","2023-0001"),
            StudentDetail(getImageID("image2"),"Alma Rosario","8","II - Malakas","San Nicolas III, Bacoor","2023-0002"),
            StudentDetail(getImageID("image3"),"Aurora Velasco","8","II - Malakas","Talaba I, Bacoor","2023-0003"),
            StudentDetail(getImageID("image4"),"Luis Ramirez","8","II - Malakas","P. F. Espiritu VIII, Bacoor","2023-0004"),
            StudentDetail(getImageID("image5"),"Angelo Diwa","7","II - Malakas","Niog III, Bacoor","2023-0005"),
            StudentDetail(getImageID("image6"),"Carmelita Dalisay","8","II - Malakas","Manggahan-Lawin, Kawit","2023-0006"),
            StudentDetail(getImageID("image7"),"Diego Gonzales","7","II - Malakas","Gahak, Kawit","2023-0007"),
            StudentDetail(getImageID("image8"),"Juan Ligaya","9","II - Malakas","Toclong I-A, Imus","2023-0008"),
            StudentDetail(getImageID("image9"),"Maria Malaya","8","II - Malakas","Zapote III, Bacoor","2023-0009"),
            StudentDetail(getImageID("image10"),"Amihan Fernandez","8","II - Malakas","Queens Row West, Bacoor","2023-0010"))

        recyclerView.adapter = StudentDetailAdapter(student)


        return binding.root
    }

    private fun getImageID(imageName: String): Int {
        val myPackage = android.content.ContextWrapper(context)
        return myPackage.resources.getIdentifier(imageName,"drawable",myPackage.packageName)
    }


}