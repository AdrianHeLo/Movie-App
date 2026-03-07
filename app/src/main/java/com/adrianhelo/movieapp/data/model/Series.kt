package com.adrianhelo.movieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("id")
    @Expose
    val seriesId: Int,

    @SerializedName("overview")
    @Expose
    val seriesOverview: String,

    @SerializedName("poster_path")
    @Expose
    val seriesPosterPath: String,

    @SerializedName("release_date")
    @Expose
    val seriesReleaseDate: String,

    @SerializedName("name")
    @Expose
    val seriesName: String,

    @SerializedName("vote_average")
    @Expose
    val seriesVoteAverage: Double
)
