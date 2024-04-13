package com.example.movieapp.domain.usecase

import androidx.paging.PagingData
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.data.repository.LatestPopularMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPopularMoviesUseCase @Inject constructor(
    private val latestPopularMovieRepository: LatestPopularMovieRepository
) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return latestPopularMovieRepository.fetchPopularMovie()
    }
}