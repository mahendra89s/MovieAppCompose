package com.example.movieapp.data.remote.services

import com.example.movieapp.data.remote.model.MovieDetail
import com.example.movieapp.data.remote.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("page") page: Int
    ): Response<MovieList>

    @GET("discover/movie")
    suspend fun fetchLatestMovies(
        @Query("page") page: Int
    ): Response<MovieList>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: String
    ): Response<MovieDetail>
}