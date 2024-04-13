package com.example.movieapp.data.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.movieapp.data.remote.datasource.MoviesDataSource
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.data.remote.model.MovieDetail
import com.example.movieapp.data.remote.model.MovieList
import com.example.movieapp.utils.DataResult
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieDataSource: MoviesDataSource
) : MoviesRepository {

    /*override suspend fun fetchLatestMovies(): PagingSource<Int,Movie> {
        return movieDataSource.fetchLatestMovies()
    }

    override suspend fun fetchPopularMovies(): PagingSource<Int,Movie> {
        return movieDataSource.fetchPopularMovies()
    }*/

    override suspend fun fetchMovieDetail(movieId : String): DataResult<MovieDetail> {
        return movieDataSource.fetchMovieDetail(movieId)
    }

}