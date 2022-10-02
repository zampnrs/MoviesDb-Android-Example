package br.zampnrs.themoviesdbapi_example.ui.movies_detail.view

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import br.zampnrs.themoviesdbapi_example.databinding.ItemMovieGenreBinding


class MovieGenresAdapter(
    private var list: List<String> = emptyList()
): RecyclerView.Adapter<MovieGenresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder {
        return MovieGenresViewHolder(ItemMovieGenreBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun onBindViewHolder(holder: MovieGenresViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

}