package com.example.movieapp.data.remote.datasource

import androidx.paging.PagingSource
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.data.remote.model.MovieDetail
import com.example.movieapp.data.remote.model.MovieList
import com.example.movieapp.utils.DataResult

interface MoviesDataSource {
    suspend fun fetchMovieDetail(movieId : String) : DataResult<MovieDetail>
}