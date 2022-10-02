package br.zampnrs.themoviesdbapi_example.ui.movies_detail.view

import android.os.Bundle
import android.view.View

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import br.zampnrs.themoviesdbapi_example.databinding.FragmentMoviesDetailBinding
import br.zampnrs.themoviesdbapi_example.utils.BaseFragment
import br.zampnrs.themoviesdbapi_example.utils.Constants
import coil.load


class MoviesDetailFragment : BaseFragment<FragmentMoviesDetailBinding>(
    FragmentMoviesDetailBinding::inflate
) {
    private val args: MoviesDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            setClickListeners()
            setContent()
        }
    }

    private fun FragmentMoviesDetailBinding.setClickListeners() {
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun FragmentMoviesDetailBinding.setContent() {
        overviewTextview.text = args.overview

        TODO("Build genres flexbox")

        if (args.hasVideo) {
            movieVideoView.visibility = View.VISIBLE
            backdropImageView.visibility = View.GONE
            TODO("Play video")
        } else {
            movieVideoView.visibility = View.GONE
            backdropImageView.apply {
                visibility = View.VISIBLE
                load("${Constants.BASE_IMG_URL}${args.backdropPath}") {
                    crossfade(true)
                    crossfade(Constants.DEFAULT_CROSSFADE_TIME)
                }
            }
        }
    }

}