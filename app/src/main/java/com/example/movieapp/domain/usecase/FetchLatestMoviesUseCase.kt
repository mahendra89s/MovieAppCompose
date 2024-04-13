package com.example.movieapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.data.remote.model.MovieList
import com.example.movieapp.data.repository.LatestPopularMovieRepository
import com.example.movieapp.data.repository.MoviesRepository
import com.example.movieapp.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchLatestMoviesUseCase @Inject constructor(
    private val latestPopularMovieRepository: LatestPopularMovieRepository
) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return latestPopularMovieRepository.fetchLatestMovie()
    }
}