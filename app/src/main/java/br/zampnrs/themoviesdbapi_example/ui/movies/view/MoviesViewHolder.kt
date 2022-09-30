package br.zampnrs.themoviesdbapi_example.ui.movies.view

import androidx.recyclerview.widget.RecyclerView

import br.zampnrs.themoviesdbapi_example.data.network.responses.MoviesResults
import br.zampnrs.themoviesdbapi_example.databinding.ItemMoviesBinding
import br.zampnrs.themoviesdbapi_example.utils.Constants

import coil.load


class MoviesViewHolder(
    val binding: ItemMoviesBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MoviesResults?) {
        movie?.let {
            binding.apply {
                moviePosterImageView.load("${Constants.BASE_IMG_URL}${it.poster_path}")
                movieTitleTextView.text = it.title
            }
        }
    }

}