package br.zampnrs.themoviesdbapi_example.ui.movies_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import br.zampnrs.themoviesdbapi_example.data.network.responses.MovieVideosResult
import br.zampnrs.themoviesdbapi_example.domain.MovieGenresUseCase
import br.zampnrs.themoviesdbapi_example.domain.MovieVideosUseCase

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class MoviesDetailViewModel @Inject constructor(
    private val movieGenresUseCase: MovieGenresUseCase,
    private val movieVideosUseCase: MovieVideosUseCase
): ViewModel() {

    sealed class ViewState {
        object Initial : ViewState()
        object Loading : ViewState()

        object MovieGenresSuccess : ViewState()
        object MovieVideosSuccess : ViewState()

        object MovieGenresError : ViewState()
        object MovieVideosError : ViewState()
    }

    private val _viewStateFlow = MutableStateFlow<ViewState>(ViewState.Initial)
    val viewStateFlow: StateFlow<ViewState> get() = _viewStateFlow

    var movieGenresList: List<String> = emptyList()
    var movieVideo: MovieVideosResult? = null

    fun loadMovieGenres(genresId: IntArray) = viewModelScope.launch {
        _viewStateFlow.value = ViewState.Loading

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
            _viewStateFlow.value = ViewState.MovieGenresSuccess
        } catch (e: Exception) {
            _viewStateFlow.value = ViewState.MovieGenresError
        }
    }

    fun loadMovieVideos(movieId: String) = viewModelScope.launch {
        _viewStateFlow.value = ViewState.Loading

        try {
            movieVideosUseCase.loadMovieVideos(movieId=movieId).also { response ->
                response.results.filter { movie -> movie.official }.also { filtered ->
                    movieVideo = if (filtered.isNotEmpty()) filtered.first()
                        else response.results.first()
                }
            }

            _viewStateFlow.value = ViewState.MovieVideosSuccess
        } catch (e: Exception) {
            _viewStateFlow.value = ViewState.MovieVideosError
        }
    }

}