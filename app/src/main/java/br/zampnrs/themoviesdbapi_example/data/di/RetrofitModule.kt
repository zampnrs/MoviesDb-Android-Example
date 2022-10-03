package br.zampnrs.themoviesdbapi_example.data.di

import br.zampnrs.themoviesdbapi_example.data.network.MovieGenresApi
import br.zampnrs.themoviesdbapi_example.data.network.MovieVideosApi
import br.zampnrs.themoviesdbapi_example.data.network.MoviesApi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    private val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun getMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun getMovieGenresApi(retrofit: Retrofit): MovieGenresApi {
        return retrofit.create(MovieGenresApi::class.java)
    }

    @Provides
    @Singleton
    fun getMovieVideosApi(retrofit: Retrofit): MovieVideosApi {
        return retrofit.create(MovieVideosApi::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
