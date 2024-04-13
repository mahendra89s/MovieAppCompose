package com.example.movieapp.di

import com.example.movieapp.data.remote.datasource.LatestMovieDataSource
import com.example.movieapp.data.remote.datasource.MoviesDataSource
import com.example.movieapp.data.remote.datasource.MoviesDataSourceImpl
import com.example.movieapp.data.remote.datasource.PopularMovieDataSource
import com.example.movieapp.data.repository.LatestPopularMovieRepository
import com.example.movieapp.data.repository.LatestPopularMovieRepositoryImpl
import com.example.movieapp.data.repository.MoviesRepository
import com.example.movieapp.data.repository.MoviesRepositoryImpl
import com.example.networksdk.data.MovieNetworkSDK
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(
        movieNetworkSDK: MovieNetworkSDK
    ): MoviesDataSource = MoviesDataSourceImpl(movieNetworkSDK)

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesDataSource: MoviesDataSource
    ): MoviesRepository = MoviesRepositoryImpl(moviesDataSource)

    @Provides
    @Singleton
    fun provideMovieNetworkSdk(): MovieNetworkSDK = MovieNetworkSDK()

    @Provides
    @Singleton
    fun provideLatestMovieDataSource(
        movieNetworkSDK: MovieNetworkSDK
    ): LatestMovieDataSource = LatestMovieDataSource(movieNetworkSDK)

    @Provides
    @Singleton
    fun providePopularMovieDataSource(
        movieNetworkSDK: MovieNetworkSDK
    ): PopularMovieDataSource = PopularMovieDataSource(movieNetworkSDK)

    @Provides
    @Singleton
    fun provideLatestPopularMovieRepository(
        latestMovieDataSource: LatestMovieDataSource,
        popularMovieDataSource: PopularMovieDataSource
    ): LatestPopularMovieRepository = LatestPopularMovieRepositoryImpl(latestMovieDataSource,popularMovieDataSource)
}