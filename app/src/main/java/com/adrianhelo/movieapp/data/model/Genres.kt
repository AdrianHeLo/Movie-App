package com.adrianhelo.movieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genres(
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String
)
