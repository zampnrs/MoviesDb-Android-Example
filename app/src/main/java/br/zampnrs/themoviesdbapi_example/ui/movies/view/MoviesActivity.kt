package br.zampnrs.themoviesdbapi_example.ui.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.zampnrs.themoviesdbapi_example.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}