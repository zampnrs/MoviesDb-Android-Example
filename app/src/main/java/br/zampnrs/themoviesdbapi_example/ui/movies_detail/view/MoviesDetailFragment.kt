package br.zampnrs.themoviesdbapi_example.ui.movies_detail.view

import android.os.Bundle
import android.view.View

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import br.zampnrs.themoviesdbapi_example.databinding.FragmentMoviesDetailBinding
import br.zampnrs.themoviesdbapi_example.ui.movies_detail.viewmodel.MoviesDetailViewModel
import br.zampnrs.themoviesdbapi_example.utils.BaseFragment
import br.zampnrs.themoviesdbapi_example.utils.Constants
import br.zampnrs.themoviesdbapi_example.utils.show

import coil.load

import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesDetailFragment : BaseFragment<FragmentMoviesDetailBinding>(
    FragmentMoviesDetailBinding::inflate
) {
    private val args: MoviesDetailFragmentArgs by navArgs()
    private val viewModel: MoviesDetailViewModel by viewModels()
    private val movieGenresAdapter = MovieGenresAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadMovieGenres(args.genresId)
        binding.apply {
            setClickListeners()
            setFlexBox()
            setContent()
            subscribeLiveData()
        }
    }

    private fun FragmentMoviesDetailBinding.setClickListeners() {
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        backdropImageView.setOnClickListener {
            viewModel.movieVideo?.let {
                playMovieVideo(it.key)
            }
        }
    }

    private fun FragmentMoviesDetailBinding.setFlexBox() {
        val flexBoxlayoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.COLUMN
            justifyContent = JustifyContent.FLEX_END
        }
        genresRecyclerView.layoutManager = flexBoxlayoutManager
    }

    private fun FragmentMoviesDetailBinding.setContent() {
        overviewTextview.text = args.overview

        if (args.hasVideo) {
            progressBar.visibility = View.VISIBLE
            viewModel.loadMovieVideos(args.movieId)
        } else {
            loadBackdropImage()
        }
    }

    private fun FragmentMoviesDetailBinding.loadBackdropImage() {
        backdropImageView.apply {
            visibility = View.VISIBLE
            load("${Constants.BASE_IMG_URL}${args.backdropPath}") {
                crossfade(true)
                crossfade(Constants.DEFAULT_CROSSFADE_TIME)
            }
        }
    }

    private fun playMovieVideo(key: String) {
        findNavController().navigate(
            MoviesDetailFragmentDirections.actionMoviesDetailFragmentToMoviePlayActivity(key)
        )
    }

    private fun FragmentMoviesDetailBinding.subscribeLiveData() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewStateFlow.collect { state ->
                when (state) {
                    is MoviesDetailViewModel.ViewState.Initial -> progressBar.show(false)
                    is MoviesDetailViewModel.ViewState.Loading -> progressBar.show(true)
                    is MoviesDetailViewModel.ViewState.MovieGenresSuccess -> {
                        progressBar.show(false)

                        movieGenresAdapter.setList(viewModel.movieGenresList)
                        genresRecyclerView.apply {
                            visibility = View.VISIBLE
                            adapter = movieGenresAdapter
                        }
                    }
                    is MoviesDetailViewModel.ViewState.MovieGenresError ->
                        genresLayout.show(false)
                    is MoviesDetailViewModel.ViewState.MovieVideosSuccess -> {
                        progressBar.show(false)
                        backdropImageView.setImageResource(android.R.drawable.ic_media_play)
                    }
                    is MoviesDetailViewModel.ViewState.MovieVideosError -> loadBackdropImage()
                }
            }
        }
    }

}