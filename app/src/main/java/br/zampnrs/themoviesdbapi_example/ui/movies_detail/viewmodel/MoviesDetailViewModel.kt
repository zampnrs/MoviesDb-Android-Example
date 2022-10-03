package br.zampnrs.themoviesdbapi_example.ui.movies_detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import br.zampnrs.themoviesdbapi_example.data.network.responses.MovieVideosResult
import br.zampnrs.themoviesdbapi_example.domain.MovieGenresUseCase
import br.zampnrs.themoviesdbapi_example.domain.MovieVideosUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesDetailViewModel @Inject constructor(
    private val movieGenresUseCase: MovieGenresUseCase,
    private val movieVideosUseCase: MovieVideosUseCase
): ViewModel() {

    sealed class ViewState {
        object GenresLoadingSuccess : ViewState()
        object GenresLoadingError : ViewState()
        object VideosLoadingSuccess : ViewState()
        object VideosLoadingError : ViewState()
    }

    val mutableLiveData = MutableLiveData<ViewState>()
    var movieGenresList: List<String> = emptyList()
    var movieVideosList: List<MovieVideosResult> = emptyList()

    fun loadMovieGenres(genresId: IntArray) = viewModelScope.launch {
        try {
            movieGenresUseCase.loadMovieGenres().also {
                it.genres.filter { genre ->
                    genresId.contains(genre.id)
                }.map { genre ->
                    genre.name
                }.also { list ->
                    movieGenresList = list
                }
            }
            mutableLiveData.postValue(ViewState.GenresLoadingSuccess)
        } catch (e: Exception) {
            mutableLiveData.postValue(ViewState.GenresLoadingError)
        }
    }

    fun loadMovieVideos(movieId: String) = viewModelScope.launch {
        try {
            movieVideosUseCase.loadMovieVideos(movieId=movieId).also {
                movieVideosList = it.results
            }

            mutableLiveData.postValue(ViewState.VideosLoadingSuccess)
        } catch (e: Exception) {
            mutableLiveData.postValue(ViewState.VideosLoadingError)
        }
    }

}