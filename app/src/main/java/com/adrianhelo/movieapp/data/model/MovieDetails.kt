package com.adrianhelo.movieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val mediaId: Int,

    @SerializedName("title")
    @Expose
    val mediaTitle: String,

    @SerializedName("overview")
    @Expose
    val mediaOverview: String,

    @SerializedName("poster_path")
    @Expose
    val mediaPosterPath: String,

    @SerializedName("release_date")
    @Expose
    val mediaReleaseDate: String,

    @SerializedName("tagline")
    @Expose
    val mediaTagline: String,

    @SerializedName("vote_average")
    @Expose
    val mediaAverage: Double,

    @SerializedName("runtime")
    @Expose
    val mediaRuntime: Int,

    @SerializedName("genres")
    @Expose
    val mediaGenres: List<Genres>
)
