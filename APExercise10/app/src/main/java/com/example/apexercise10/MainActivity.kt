package com.example.apexercise10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.apexercise10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentDisplay = StudentListFragment()
        val fragmentDetail = StudentDetailFragment()

//        val student = StudentList.student
//
//
//        recyclerView = StudentDetailAdapter(student)
//        recyclerView.layoutManager = LinearLayoutManager(this)
        
        
//        val student = StudentInfo.student
//
//        val listenerAdapter = StudentDetailAdapter(student)
//        recyclerView.adapter = listenerAdapter

        supportFragmentManager.beginTransaction().apply {
//            var mBundle = Bundle()
//            mBundle.putString("maintext",maintext)
//            f1.arguments = mBundle
            replace(R.id.fragmentContainerView,fragmentDisplay)
            commit()
        }



//        listenerAdapter.setOnClickListener(object :
//            StudentDetailAdapter.OnClickListener {
//            override fun onClick(position: Int, model: StudentDetail) {
//                //val fragmentDisplay = StudentListFragment()
//
//                supportFragmentManager.beginTransaction().apply {
//                    var mBundle = Bundle()
//                    //mBundle.putParcelable("myArray",student)
//                    replace(R.id.fragmentContainerView,fragmentDetail)
//                    commit()
//                }
//                Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
//            }
//        })

    }

    companion object{
        val NEXT_SCREEN="details_screen"
    }
//
//    private fun getImageID(imageName: String):Int{
//        return resources.getIdentifier(imageName,"drawable",packageName)
//    }
}