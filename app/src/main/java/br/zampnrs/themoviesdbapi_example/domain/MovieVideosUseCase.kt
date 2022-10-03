package br.zampnrs.themoviesdbapi_example.domain

import br.zampnrs.themoviesdbapi_example.BuildConfig
import br.zampnrs.themoviesdbapi_example.data.network.MovieVideosApi

import javax.inject.Inject


class MovieVideosUseCase @Inject constructor(
    private val api: MovieVideosApi
) {

    suspend fun loadMovieVideos(
        key: String = BuildConfig.MOVIESDB_API_KEY,
        language: String? = null,
        movieId: String
    ) = api.loadMovieVideos(key, language, movieId)

}