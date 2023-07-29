package com.data.apexcercise15

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.data.apexcercise15.databinding.MovieItemLayoutBinding
import com.squareup.picasso.Picasso
import java.lang.Exception

class MovieItemViewHolder(private val binding: MovieItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    fun movieSearchBinding(movieDetails:Movie){
        //val imageLink = "https://m.media-amazon.com/images/M/MV5BNWZkMjYzNjctYTMyZi00MjdjLTg5MzMtYjdhM2M0MTI5ZWFkXkEyXkFqcGdeQXVyNTc4MjczMTM@._V1_SX300.jpg"
        var imageLink = movieDetails.Poster

//        //binding.imgPoster.setImageURI(movieDetails.Poster.toUri())

//        Picasso.get()
//            .load(imageLink)
//            .into(binding.imgPoster)

        try {
            if(movieDetails.Poster=="N/A"){
                binding.imgPoster.setImageResource(getImageID("no_image", binding.root.context))
            }else{
                Glide.with(binding.root)
                    //.load(movieDetails.Poster)
                    .load(imageLink)
                    .apply(RequestOptions.overrideOf(100,100))
                    .into(binding.imgPoster)
            }
        }catch (e:Exception){
            Log.d("MYMOVIE",e.toString())
        }

        binding.txtMovieTitle.text =movieDetails.Title
        binding.txtMovieActor.text =movieDetails.Actors
        binding.txtMovieRelease.text =movieDetails.Released
        binding.txtMovieLanguage.text =movieDetails.Language
    }

    private fun getImageID(imageName: String,context: Context): Int {
        val myPackage = android.content.ContextWrapper(context)
        return myPackage.resources.getIdentifier(imageName,"drawable",myPackage.packageName)
    }
}