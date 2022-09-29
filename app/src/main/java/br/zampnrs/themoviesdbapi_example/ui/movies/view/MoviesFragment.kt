package br.zampnrs.themoviesdbapi_example.ui.movies.view

import android.os.Bundle
import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager

import br.zampnrs.themoviesdbapi_example.databinding.FragmentMoviesBinding
import br.zampnrs.themoviesdbapi_example.utils.BaseFragment


class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setRecyclerView()
    }

    private fun FragmentMoviesBinding.setRecyclerView() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}