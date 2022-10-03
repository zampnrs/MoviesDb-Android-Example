package br.zampnrs.themoviesdbapi_example.data.network

import br.zampnrs.themoviesdbapi_example.data.network.responses.MovieVideosResponse

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieVideosApi {

    @GET("movie/{movie_id}/videos")
    suspend fun loadMovieVideos(
        @Query("api_key") key: String,
        @Query("language") language: String?,
        @Path("movie_id") movie_id: String
    ): MovieVideosResponse

}