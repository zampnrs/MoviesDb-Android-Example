package br.zampnrs.themoviesdbapi_example.data.network.responses

import com.google.gson.annotations.SerializedName

data class MovieGenresResponse(
    @SerializedName("genres") val genres: List<MovieGenres>
)
