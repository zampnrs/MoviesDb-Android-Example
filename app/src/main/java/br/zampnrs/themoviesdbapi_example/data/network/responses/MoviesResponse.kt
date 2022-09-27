package br.zampnrs.themoviesdbapi_example.data.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MoviesResponse(
    @SerializedName("dates") val dates: MoviesDates,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MoviesResults>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
): Parcelable
