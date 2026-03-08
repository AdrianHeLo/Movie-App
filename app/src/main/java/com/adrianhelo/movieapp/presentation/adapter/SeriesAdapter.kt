package com.adrianhelo.movieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.data.model.Series
import com.adrianhelo.movieapp.databinding.MovieItemListBinding
import com.bumptech.glide.Glide

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    private var seriesList = listOf<Series>()

    fun submitList(list: List<Series>) {
        seriesList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemListBinding: MovieItemListBinding): RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(series: Series){
            itemListBinding.titleMovieItemList.text = series.seriesName
            itemListBinding.averageMovieItemList.text = series.seriesVoteAverage.toString()
            val imageUrl = "https://image.tmdb.org/t/p/w500${series.seriesPosterPath}"
            Glide.with(itemListBinding.imageMovieItemList.context)
                .load(imageUrl)
                .into(itemListBinding.imageMovieItemList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieItemListBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.movie_item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(seriesList[position])
    }
}