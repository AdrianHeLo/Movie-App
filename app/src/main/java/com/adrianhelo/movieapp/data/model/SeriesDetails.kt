package com.adrianhelo.movieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SeriesDetails(
    val id: Int,

    val seasons: List<Seasons>,

    @SerializedName("name")
    @Expose
    val seriesName: String,

    @SerializedName("overview")
    @Expose
    val seriesOverview: String,

    @SerializedName("poster_path")
    @Expose
    val seriesPosterPath: String,

    @SerializedName("tagline")
    @Expose
    val seriesTagline: String,

    @SerializedName("vote_average")
    @Expose
    val seriesAverage: Double,

    @SerializedName("genres")
    @Expose
    val seriesGenres: List<Genres>,

    @SerializedName("number_of_seasons")
    @Expose
    val numberOfSeasons: Int,
)
