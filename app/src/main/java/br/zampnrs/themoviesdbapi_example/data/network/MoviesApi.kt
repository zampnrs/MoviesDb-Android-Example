package br.zampnrs.themoviesdbapi_example.data.network

import br.zampnrs.themoviesdbapi_example.data.network.responses.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {

    @GET("movie/upcoming")
    suspend fun loadMovies(
        @Query("api_key") key: String,
        @Query("language") language: String?,
        @Query("region") region: String?,
        @Query("page") page: Int?
    ): MoviesResponse

}
