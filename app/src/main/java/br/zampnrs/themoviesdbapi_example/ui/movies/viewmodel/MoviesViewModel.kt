package br.zampnrs.themoviesdbapi_example.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn

import br.zampnrs.themoviesdbapi_example.data.network.responses.MoviesResults
import br.zampnrs.themoviesdbapi_example.domain.MoviesUseCase
import br.zampnrs.themoviesdbapi_example.ui.movies.view.MoviesPagingSource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCase: MoviesUseCase
) : ViewModel() {

    fun loadMovies(): Flow<PagingData<MoviesResults>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MoviesPagingSource(useCase) }
    ).flow.cachedIn(viewModelScope)

}