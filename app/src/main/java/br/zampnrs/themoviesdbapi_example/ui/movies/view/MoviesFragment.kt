package br.zampnrs.themoviesdbapi_example.ui.movies.view

import android.os.Bundle
import android.view.View

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import br.zampnrs.themoviesdbapi_example.R
import br.zampnrs.themoviesdbapi_example.data.network.responses.MoviesResults
import br.zampnrs.themoviesdbapi_example.databinding.FragmentMoviesBinding
import br.zampnrs.themoviesdbapi_example.ui.movies.viewmodel.MoviesViewModel
import br.zampnrs.themoviesdbapi_example.utils.BaseFragment
import br.zampnrs.themoviesdbapi_example.utils.showToast

import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import retrofit2.HttpException


@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()
    private val moviesAdapter = MoviesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setRecyclerView()
        loadAdapterContent()
    }

    private fun FragmentMoviesBinding.setRecyclerView() {
        moviesAdapter.onSelectMovie = ::onMovieSelected
        moviesRecyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onMovieSelected(movie: MoviesResults?) {
        movie?.let {
            findNavController().navigate(
                MoviesFragmentDirections.actionMoviesFragmentToMoviesDetailFragment(
                    it.overview, it.genre_ids.toIntArray(), it.id, it.video, it.backdrop_path
                )
            )
        }
    }

    private fun loadAdapterContent() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadMovies().catch { cause ->
                when (cause) {
                    is HttpException -> showToast(getString(R.string.network_error))
                    else -> showToast(getString(R.string.movie_loading_error))
                }
            }.collectLatest {
                moviesAdapter.submitData(it)
            }
        }
    }

}