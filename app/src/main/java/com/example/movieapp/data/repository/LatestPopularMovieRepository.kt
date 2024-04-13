package com.example.movieapp.data.repository

import androidx.paging.PagingData
import com.example.movieapp.data.remote.model.Movie
import kotlinx.coroutines.flow.Flow

interface LatestPopularMovieRepository {
    fun fetchLatestMovie(): Flow<PagingData<Movie>>
    fun fetchPopularMovie(): Flow<PagingData<Movie>>
}