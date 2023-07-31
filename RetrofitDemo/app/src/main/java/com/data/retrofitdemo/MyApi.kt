package com.data.retrofitdemo

import retrofit2.Call
import retrofit2.http.GET

interface MyApi {
    @GET("comments")
    fun getComments():Call<List<Comments>>

//    @GET("users")
//    fun getUsers():Call<List<Comments>>

}