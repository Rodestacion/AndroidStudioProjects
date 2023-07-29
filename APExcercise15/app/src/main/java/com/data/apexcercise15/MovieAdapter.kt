package com.data.apexcercise15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.data.apexcercise15.databinding.MovieItemLayoutBinding

class MovieAdapter(private val movie:List<Movie>):RecyclerView.Adapter<MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemLayoutBinding.inflate(inflater,parent,false)
        return MovieItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.movieSearchBinding(movie[position])
    }

}