package br.zampnrs.themoviesdbapi_example.ui.movies_detail.view

import androidx.recyclerview.widget.RecyclerView
import br.zampnrs.themoviesdbapi_example.databinding.ItemMovieGenreBinding
import javax.inject.Inject


class MovieGenresViewHolder(
    private val binding: ItemMovieGenreBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(genre: String) {
        binding.genreTextView.text = genre
    }

}