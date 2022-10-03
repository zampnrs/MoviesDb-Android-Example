package br.zampnrs.themoviesdbapi_example.ui.movie_play

import android.os.Bundle

import androidx.navigation.navArgs

import br.zampnrs.themoviesdbapi_example.BuildConfig
import br.zampnrs.themoviesdbapi_example.R
import br.zampnrs.themoviesdbapi_example.databinding.ActivityMoviePlayBinding
import br.zampnrs.themoviesdbapi_example.utils.showToast
import com.google.android.youtube.player.YouTubeBaseActivity

import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer


class MoviePlayActivity : YouTubeBaseActivity() {
    private lateinit var binding: ActivityMoviePlayBinding
    private val args: MoviePlayActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviePlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moviePlayerView.initialize(BuildConfig.YOUTUBE_API_KEY, playVideo())
    }

    private fun playVideo() = object : YouTubePlayer.OnInitializedListener {
        override fun onInitializationSuccess(
            provider: YouTubePlayer.Provider?,
            player: YouTubePlayer?,
            wasRestored: Boolean
        ) {
            player?.apply {
                loadVideo(args.videoKey)
                play()
            }
        }

        override fun onInitializationFailure(
            provider: YouTubePlayer.Provider?,
            result: YouTubeInitializationResult?
        ) {
            showToast(getString(R.string.video_player_error_message))
        }

    }

}
