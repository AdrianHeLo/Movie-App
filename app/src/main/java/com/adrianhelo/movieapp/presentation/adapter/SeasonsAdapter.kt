package com.adrianhelo.movieapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.movieapp.data.model.Seasons
import com.adrianhelo.movieapp.databinding.SeasonsItemListBinding
import com.bumptech.glide.Glide


class SeasonsAdapter(private val seasonsList: List<Seasons>): RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder>() {

    inner class SeasonsViewHolder(private val itemListBinding: SeasonsItemListBinding): RecyclerView.ViewHolder(itemListBinding.root){
        fun bind(seasons: Seasons){
            itemListBinding.seasonNameText.text = seasons.seasonsName

            val voteAverage = seasons.seriesAverage
            val rating = (voteAverage.div(2)).toFloat()
            itemListBinding.seasonsRatingBar.rating = rating

            val imageUrl = "https://image.tmdb.org/t/p/w500${seasons.seasonsPosterPath}"
            Glide.with(itemView.context).load(imageUrl).into(itemListBinding.seasonImageView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeasonsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SeasonsItemListBinding = DataBindingUtil.inflate(layoutInflater, com.adrianhelo.movieapp.R.layout.seasons_item_list, parent, false)
        return SeasonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        return holder.bind(seasonsList[position])
    }

    override fun getItemCount(): Int {
        return seasonsList.size
    }
}