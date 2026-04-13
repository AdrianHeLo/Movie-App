package com.adrianhelo.movieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.data.model.MovieDetails
import com.adrianhelo.movieapp.databinding.MovieItemListBinding
import com.bumptech.glide.Glide

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList = listOf<Movie>()

    fun submitList(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemListBinding: MovieItemListBinding): RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(movie: Movie){
            itemListBinding.titleMovieItemList.text = movie.movieTitle
            itemListBinding.averageMovieItemList.text = movie.movieVoteAverage.toString()
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.moviePosterPath}"
            Glide.with(itemListBinding.imageMovieItemList.context)
                .load(imageUrl)
                .into(itemListBinding.imageMovieItemList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieItemListBinding = DataBindingUtil.inflate(layoutInflater,R.layout.movie_item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Movie ID: ${movieList[position].movieId}", Toast.LENGTH_LONG).show()
        }
        return holder.bind(movieList[position])
    }
}