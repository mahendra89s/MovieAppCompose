package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.remote.datasource.LatestMovieDataSource
import com.example.movieapp.data.remote.datasource.PopularMovieDataSource
import com.example.movieapp.data.remote.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LatestPopularMovieRepositoryImpl @Inject constructor(
    private val latestMovieDataSource: LatestMovieDataSource,
    private val popularMovieDataSource: PopularMovieDataSource
) : LatestPopularMovieRepository {
    override fun fetchLatestMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                latestMovieDataSource
            }
        ).flow
    }

    override fun fetchPopularMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                popularMovieDataSource
            }
        ).flow
    }

}