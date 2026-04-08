package com.adrianhelo.movieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.movieapp.data.model.Media
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.databinding.MediaItemListBinding
import com.adrianhelo.movieapp.databinding.MovieItemListBinding
import com.bumptech.glide.Glide

class MediaAdapter: RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {

    private var mediaList = listOf<Media>()

    fun submitList(list: List<Media>) {
        mediaList = list
        notifyDataSetChanged()
    }

    inner class MediaViewHolder(private val itemListBinding: MediaItemListBinding): RecyclerView.ViewHolder(itemListBinding.root){
        val itemTitle = itemListBinding.titleItemList
        val itemAverage = itemListBinding.averageItemList
        val itemImage = itemListBinding.imageItemList
        val itemMedia = itemListBinding.mediaItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = MediaItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val getItem = mediaList[position]
        holder.itemMedia.text = getItem.mediaType
        holder.itemTitle.text = getItem.displayTitle
        holder.itemAverage.text = getItem.mediaVoteAverage.toString()
        val imageUrl = "https://image.tmdb.org/t/p/w500${getItem.mediaPosterPath}"
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }
}