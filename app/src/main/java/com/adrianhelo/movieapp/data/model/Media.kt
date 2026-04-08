package com.adrianhelo.movieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("id")
    @Expose
    val mediaId: Int,

    @SerializedName("media_type")
    @Expose
    val mediaType: String,

    @SerializedName("overview")
    @Expose
    val mediaOverview: String?,

    @SerializedName("poster_path")
    @Expose
    val mediaPosterPath: String?,

    @SerializedName("release_date")
    @Expose
    val mediaReleaseDate: String?,

    @SerializedName("title")
    @Expose
    val mediaTitle: String?,

    @SerializedName("name")
    @Expose
    val mediaName: String?,

    @SerializedName("vote_average")
    @Expose
    val mediaVoteAverage: Double?
){
    // Esta es la variable que tu Adapter va a leer
    val displayTitle: String
        get() = mediaTitle ?: mediaName ?: "NA"
}
