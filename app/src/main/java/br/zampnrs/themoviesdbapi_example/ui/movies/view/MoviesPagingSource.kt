package br.zampnrs.themoviesdbapi_example.ui.movies.view

import androidx.paging.PagingSource
import androidx.paging.PagingState

import br.zampnrs.themoviesdbapi_example.data.network.responses.MoviesResults
import br.zampnrs.themoviesdbapi_example.domain.MoviesUseCase
import br.zampnrs.themoviesdbapi_example.utils.Constants

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MoviesPagingSource @Inject constructor(
    private val useCase: MoviesUseCase
): PagingSource<Int, MoviesResults>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesResults>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResults> {
        return try {
            val response = useCase.loadMovies(page = params.key)

            val previousKey = if (response.page == Constants.DEFAULT_PAGE_INDEX) null
                else response.page - Constants.DEFAULT_PAGE_INDEX
            val nextKey = if (response.page == response.total_pages) null
                else response.page + Constants.DEFAULT_PAGE_INDEX

            LoadResult.Page(
                data = response.results,
                prevKey = previousKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}