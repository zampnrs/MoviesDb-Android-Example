package br.zampnrs.themoviesdbapi_example.domain

import br.zampnrs.themoviesdbapi_example.BuildConfig
import br.zampnrs.themoviesdbapi_example.data.network.MoviesApi
import br.zampnrs.themoviesdbapi_example.utils.Constants

import javax.inject.Inject


class MoviesUseCase @Inject constructor(
    private val api: MoviesApi
) {

    suspend fun loadMovies(
        key: String = BuildConfig.API_KEY,
        language: String? = null,
        region: String? = null,
        page: Int? = Constants.FIRST_PAGE_INDEX
    ) = api.loadMovies(key, language, region, page)
}