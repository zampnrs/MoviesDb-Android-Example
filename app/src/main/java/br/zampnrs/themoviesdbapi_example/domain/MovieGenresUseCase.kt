package br.zampnrs.themoviesdbapi_example.domain

import br.zampnrs.themoviesdbapi_example.BuildConfig
import br.zampnrs.themoviesdbapi_example.data.network.MovieGenresApi

import javax.inject.Inject

class MovieGenresUseCase @Inject constructor(
    private val api: MovieGenresApi
) {

    suspend fun loadMovieGenres(
        key: String = BuildConfig.API_KEY,
        language: String? = null,
    ) = api.getMovieGenres(key, language)

}