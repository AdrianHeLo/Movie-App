package com.adrianhelo.movieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.data.model.Series
import com.adrianhelo.movieapp.databinding.SeriesItemListBinding
import com.bumptech.glide.Glide

class SeriesAdapter(val onSeriesClick: (Int)-> Unit): RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    private var seriesList = listOf<Series>()

    fun submitList(list: List<Series>) {
        seriesList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemListBinding: SeriesItemListBinding): RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(series: Series){
            itemListBinding.nameSeriesItemList.text = series.seriesName
            itemListBinding.averageSeriesItemList.text = series.seriesVoteAverage.toString()
            val imageUrl = "https://image.tmdb.org/t/p/w500${series.seriesPosterPath}"
            Glide.with(itemListBinding.imageSeriesItemList.context)
                .load(imageUrl)
                .into(itemListBinding.imageSeriesItemList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SeriesItemListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.series_item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onSeriesClick(seriesList[position].seriesId)
        }
        return holder.bind(seriesList[position])
    }
}