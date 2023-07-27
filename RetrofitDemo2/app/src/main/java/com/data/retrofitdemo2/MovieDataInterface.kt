package com.data.retrofitdemo2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataInterface {
    @GET ("/?")
    fun getMovieDetails(@Query("apikey")apiKey:String,@Query("t")title:String): Call<MovieData>
    //fun getMovieDetails(@Query("apikey=")apiKey:String,@Query("t")title:String): Call<MovieData>
    fun getRatingDetails(@Query("apikey")apiKey:String,@Query("t")title:String): Call<Rating>
}