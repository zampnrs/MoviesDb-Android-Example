package br.zampnrs.themoviesdbapi_example.ui.movies_detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import br.zampnrs.themoviesdbapi_example.domain.MovieGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesDetailViewModel @Inject constructor(
    private val useCase: MovieGenresUseCase
): ViewModel() {

    sealed class ViewState {
        object GenresLoadingSuccess : ViewState()
        object GenresLoadingError : ViewState()
    }

    val mutableLiveData = MutableLiveData<ViewState>()
    var movieGenresList: List<String> = emptyList()

    fun loadMovieGenres(genresId: IntArray) = viewModelScope.launch {
        try {
            useCase.loadMovieGenres().also {
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

}