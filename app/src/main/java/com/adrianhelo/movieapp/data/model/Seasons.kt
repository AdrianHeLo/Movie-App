package com.adrianhelo.movieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Seasons(
    @SerializedName("name")
    @Expose
    val seasonsName: String,

    @SerializedName("overview")
    @Expose
    val seasonsOverview: String,

    @SerializedName("poster_path")
    @Expose
    val seasonsPosterPath: String,

    @SerializedName("season_number")
    @Expose
    val seasonsNumber: Int,

    @SerializedName("vote_average")
    @Expose
    val seriesAverage: Double,
)
