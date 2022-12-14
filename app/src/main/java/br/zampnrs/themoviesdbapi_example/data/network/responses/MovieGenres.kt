package br.zampnrs.themoviesdbapi_example.data.network.responses

import com.google.gson.annotations.SerializedName


data class MovieGenres(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
