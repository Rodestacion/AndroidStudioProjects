package com.data.retrofitdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.data.retrofitdemo2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private val BASE_URL = "http://www.omdbapi.com/"
    private val API_KEY = "dae7afc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMovie("Spiderman")


    }

    private fun getMovie(title:String){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDataInterface::class.java)

        api.getMovieDetails(API_KEY,title).enqueue(object : Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>
            ) {
                if(response.isSuccessful){
                    var movieData = response.body()
                    Log.d("MYMOVIE",movieData.toString())
                }else{
                    Log.d("MYMOVIE","RESPONSE IS NOT SUCCESSFUL")
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.d("MYMOVIE","ERROR")
            }

        })

    }
}