package br.zampnrs.themoviesdbapi_example.data.network.responses

import com.google.gson.annotations.SerializedName


data class MovieVideosResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<MovieVideosResult>
)
