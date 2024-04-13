package com.example.movieapp.domain.usecase

import com.example.movieapp.data.remote.model.MovieDetail
import com.example.movieapp.data.repository.MoviesRepository
import com.example.movieapp.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
){
    operator fun invoke(id : String): Flow<DataResult<MovieDetail>> {
        return flow {
            emit(moviesRepository.fetchMovieDetail(id))
        }
    }
}