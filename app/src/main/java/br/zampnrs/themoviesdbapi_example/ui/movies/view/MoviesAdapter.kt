package br.zampnrs.themoviesdbapi_example.ui.movies.view

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import br.zampnrs.themoviesdbapi_example.data.network.responses.MoviesResults
import br.zampnrs.themoviesdbapi_example.databinding.ItemMoviesBinding


class MoviesAdapter : PagingDataAdapter<MoviesResults, MoviesViewHolder>(CONTENT_COMPARATOR) {
    var onSelectMovie: ((movie: MoviesResults?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(ItemMoviesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onSelectMovie?.invoke(getItem(position))
        }
    }

    companion object {
        private val CONTENT_COMPARATOR = object : DiffUtil.ItemCallback<MoviesResults>() {
            override fun areItemsTheSame(
                oldItem: MoviesResults,
                newItem: MoviesResults
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MoviesResults,
                newItem: MoviesResults
            ): Boolean = oldItem == newItem
        }
    }

}