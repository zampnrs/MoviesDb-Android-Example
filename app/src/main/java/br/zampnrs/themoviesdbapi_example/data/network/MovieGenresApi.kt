package br.zampnrs.themoviesdbapi_example.data.network

import br.zampnrs.themoviesdbapi_example.data.network.responses.MovieGenresResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieGenresApi {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") key: String,
        @Query("language") language: String?
    ): MovieGenresResponse

}