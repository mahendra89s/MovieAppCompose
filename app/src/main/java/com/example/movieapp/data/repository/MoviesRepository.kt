package com.example.movieapp.data.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.data.remote.model.MovieDetail
import com.example.movieapp.utils.DataResult

interface MoviesRepository {
    /*suspend fun fetchLatestMovies() : PagingSource<Int,Movie>
    suspend fun fetchPopularMovies() : PagingSource<Int,Movie>*/
    suspend fun fetchMovieDetail(movieId : String) : DataResult<MovieDetail>
}