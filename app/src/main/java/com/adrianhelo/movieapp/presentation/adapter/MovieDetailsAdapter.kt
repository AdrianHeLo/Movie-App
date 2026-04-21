package com.adrianhelo.movieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.movieapp.data.model.MovieDetails
import com.adrianhelo.movieapp.databinding.MovieDetailsBinding
import com.bumptech.glide.Glide

class MovieDetailsAdapter: RecyclerView.Adapter<MovieDetailsAdapter.MovieDetailsVH>() {

    private var movieDetails = listOf<MovieDetails>()

    fun submitList(list: List<MovieDetails>) {
        movieDetails = list
        notifyDataSetChanged()
    }

    inner class MovieDetailsVH(private val itemMovieDetailsBinding: MovieDetailsBinding): RecyclerView.ViewHolder(itemMovieDetailsBinding.root){
        fun bind(movie: MovieDetails){
            itemMovieDetailsBinding.titleMediaDetails.text = movie.mediaTitle
            itemMovieDetailsBinding.overviewMediaDetails.text = movie.mediaOverview
            itemMovieDetailsBinding.runtimeMediaDetails.text = movie.mediaRuntime.toString()
            itemMovieDetailsBinding.taglineMediaDetails.text = movie.mediaTagline
            itemMovieDetailsBinding.gengreMediaDetails.text = movie.mediaGenres.toString()
            itemMovieDetailsBinding.releaseDateMediaDetails.text = movie.mediaReleaseDate

            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.mediaPosterPath}"
            Glide.with(itemMovieDetailsBinding.imageMediaDetails.context)
                .load(imageUrl)
                .into(itemMovieDetailsBinding.imageMediaDetails)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsVH {
        val binding = MovieDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieDetailsVH(binding)
    }

    override fun getItemCount(): Int {
        return movieDetails.size
    }

    override fun onBindViewHolder(holder: MovieDetailsVH, position: Int) {
        val itemPosition = movieDetails[position]
        return holder.bind(itemPosition)
    }
}