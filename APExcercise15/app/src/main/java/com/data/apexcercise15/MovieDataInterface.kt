package com.data.apexcercise15

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataInterface {
    @GET("/")
    fun getMovieDetails(@Query("apikey")apiKey:String, @Query("t")title:String,@Query("y")year:String): Call<MovieData>

    @GET("/")
    fun getRatingDetails(@Query("apikey")apiKey:String, @Query("t")title:String,@Query("y")year:String): Call<Rating>
}