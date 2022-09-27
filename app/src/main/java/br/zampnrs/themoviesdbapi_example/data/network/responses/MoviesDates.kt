package br.zampnrs.themoviesdbapi_example.data.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MoviesDates(
    @SerializedName("maximum") val maximum: String,
    @SerializedName("minimum") val minimum: String
): Parcelable
