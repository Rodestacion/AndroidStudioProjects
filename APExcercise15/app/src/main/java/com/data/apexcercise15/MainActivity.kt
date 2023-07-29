package com.data.apexcercise15

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.apexcercise15.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Year


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val BASE_URL = "http://www.omdbapi.com/"
    private val API_KEY = "dae7afc"
    private val SEARCH_DELAY : Long = 15000 // in milliseconds
    private var START_YEAR = 1950
    private lateinit var yearToday: Year
    private var yearList = mutableListOf<String>()
    private var defaultItemListYear:String =""
    var searchMovie = mutableListOf<Movie>()
    private lateinit var myAdapter:MovieAdapter

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        yearToday = Year.now()
        setYearList()

        recyclerView = binding.searchMovieRecyclerView
        refreshRecyclerView()

        binding.yearListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                defaultItemListYear = yearList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                defaultItemListYear
            }


        }
        
        binding.btnSearch.setOnClickListener {
            binding.btnSearch.visibility = View.INVISIBLE
            binding.searchProgress.visibility = View.VISIBLE

            if(binding.etTitle.text.isEmpty()){
                Toast.makeText(applicationContext, "No Movie title to search", Toast.LENGTH_SHORT).show()
                binding.btnSearch.visibility = View.VISIBLE
                binding.searchProgress.visibility = View.INVISIBLE
            }else{
                if (defaultItemListYear!="-- All --"){
                    searchMovie.clear()
                    refreshRecyclerView()
                    Toast.makeText(applicationContext, "Clearing Data", Toast.LENGTH_SHORT).show()
                    getSpecificYear(defaultItemListYear)
                }else{
                    //searchMovie.clear()
                    //refreshRecyclerView()
                    //Toast.makeText(applicationContext, "Clearing Data", Toast.LENGTH_SHORT).show()
                    getAllData()
                }
            }
        }

    }

//    override fun onPause() {
//        super.onPause()
//        refreshRecyclerView()
//    }

    private fun refreshRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = MovieAdapter(searchMovie)
        myAdapter.notifyDataSetChanged()
        recyclerView.adapter = myAdapter
    }

//    @SuppressLint("NotifyDataSetChanged")
    private fun getSpecificYear(year: String) {

        getMovie(binding.etTitle.text.toString(), year)
        refreshRecyclerView()
        if (searchMovie.size>0){
            myAdapter = MovieAdapter(searchMovie)
            recyclerView.adapter = myAdapter
            Toast.makeText(applicationContext, "Check the list of movie above", Toast.LENGTH_SHORT).show()
            myAdapter.notifyDataSetChanged()
        }else{
            Toast.makeText(applicationContext, "No Movie found", Toast.LENGTH_SHORT).show()
        }

        binding.btnSearch.visibility = View.VISIBLE
        binding.searchProgress.visibility = View.INVISIBLE
    }

//    @SuppressLint("NotifyDataSetChanged")
    private fun getAllData() {
        //refreshRecyclerView()

            var tempYear = yearToday.toString().toInt()
            try {
                while (tempYear>=START_YEAR) {
                    CoroutineScope(Dispatchers.IO).launch {
                        getMovie(binding.etTitle.text.toString(), tempYear.toString())
                        //refreshRecyclerView()
                        delay(SEARCH_DELAY)
                    }
                    tempYear--
                }

            }catch (e:Exception){
                Log.d("MYMOVIE",e.toString())
                Toast.makeText(applicationContext, "Error in Network Connection", Toast.LENGTH_SHORT).show()
            }

            //refreshRecyclerView()

            Toast.makeText(applicationContext,searchMovie.size, Toast.LENGTH_SHORT).show()

            if (searchMovie.size>0){
                myAdapter = MovieAdapter(searchMovie)
                recyclerView.adapter = myAdapter
                Toast.makeText(applicationContext, "Check the list of movie above", Toast.LENGTH_SHORT).show()
                myAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(applicationContext, "No Movie found", Toast.LENGTH_SHORT).show()
            }


        binding.btnSearch.visibility = View.VISIBLE
        binding.searchProgress.visibility = View.INVISIBLE
    }

    private fun setYearList(){
        var tempYear = yearToday.toString().toInt()
        yearList.add("-- All --")

        while (tempYear>START_YEAR){
            yearList.add(tempYear.toString())
            tempYear--
        }

        val spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,yearList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.yearListSpinner.adapter = spinnerAdapter
        defaultItemListYear = yearList[0]
    }

    private fun getMovie(title: String, year: String){

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDataInterface::class.java)

        api.getMovieDetails(API_KEY,title,year).enqueue(
            object : Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>){
                //Log.d("MYMOVIE",response.code().toString())
                if(response.isSuccessful){
                    var movieData = response.body()

                    if (movieData != null) {
                        if(movieData.Title!= null){
                            Log.d("MYMOVIE",movieData.toString())
                            var search = Movie(movieData.imdbID,movieData.Poster,movieData.Title,movieData.Actors,movieData.Released,movieData.Language)
                            searchMovie.add(search)
                        }
                    }
                }else{

                    Log.d("MYMOVIE","RESPONSE IS NOT SUCCESSFUL")
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.d("MYMOVIE","ERROR")
            }
        })

    }

//    private fun getRating(title: String, year: String){
//
//
//        val api = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(MovieDataInterface::class.java)
//
//        api.getRatingDetails(API_KEY,title,year).enqueue(object :Callback<Rating>{
//            override fun onResponse(call: Call<Rating>, response: Response<Rating>) {
//                if(response.isSuccessful){
//                    var movieRating = response.body()
//                    Log.d("MYMOVIE",movieRating.toString())
//
//                }else{
//                    Toast.makeText(applicationContext, "No rating found", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Rating>, t: Throwable) {
//                Log.d("MYMOVIE","ERROR")
//                Toast.makeText(applicationContext, "Error in connection. Check network connection", Toast.LENGTH_SHORT).show()
//            }
//        })
//
//    }

}