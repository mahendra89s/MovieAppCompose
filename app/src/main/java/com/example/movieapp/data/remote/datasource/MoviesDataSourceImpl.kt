package com.example.movieapp.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.data.remote.model.MovieDetail
import com.example.movieapp.data.remote.services.ApiServices
import com.example.movieapp.utils.DataResult
import com.example.networksdk.data.MovieNetworkSDK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(
    movieNetworkSDK: MovieNetworkSDK
) : MoviesDataSource {

    private val songsApiService by lazy { movieNetworkSDK.retrofit.create(ApiServices::class.java) }
    /*override suspend fun fetchLatestMovies(): PagingSource<Int, Movie> {
        return object : PagingSource<Int, Movie>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
                val page = params.key ?: 1
                val response = songsApiService.fetchLatestMovies()
                return try {
                    LoadResult.Page(
                        data = response.body()?.movies ?: listOf(),
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (response.body()?.movies?.isEmpty() == true) null else page.plus(
                            1
                        )
                    )
                } catch (e: IOException) {
                    LoadResult.Error(
                        e
                    )
                } catch (e: HttpException) {
                    LoadResult.Error(
                        e
                    )
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                return state.anchorPosition
            }

        }
        *//*return withContext(Dispatchers.IO) {
            try {
                val response = songsApiService.fetchLatestMovies()
                if (response.isSuccessful) {
                    DataResult.Success(response.body()!!)
                } else {
                    DataResult.Error(response.message())
                }
            } catch (exception: Exception) {
                DataResult.Error(
                    exception.message ?: ""
                )
            }
        }*//*

    }

    override suspend fun fetchPopularMovies(): PagingSource<Int, Movie> {
        return object : PagingSource<Int, Movie>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
                val page = params.key ?: 1
                val response = songsApiService.fetchLatestMovies()
                return try {
                    LoadResult.Page(
                        data = response.body()?.movies ?: listOf(),
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (response.body()?.movies?.isEmpty() == true) null else page.plus(
                            1
                        )
                    )
                } catch (e: IOException) {
                    LoadResult.Error(
                        e
                    )
                } catch (e: HttpException) {
                    LoadResult.Error(
                        e
                    )
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                return state.anchorPosition
            }

        }
        *//*return withContext(Dispatchers.IO) {
            try {
                val response = songsApiService.fetchPopularMovies()
                if (response.isSuccessful) {
                    DataResult.Success(response.body()!!)
                } else {
                    DataResult.Error(response.message())
                }
            } catch (exception: Exception) {
                DataResult.Error(
                    exception.message ?: ""
                )
            }
        }*//*
    }*/

    override suspend fun fetchMovieDetail(movieId: String): DataResult<MovieDetail> {
        return withContext(Dispatchers.IO) {
            try {
                val response = songsApiService.fetchMovieDetails(movieId)
                if (response.isSuccessful) {
                    DataResult.Success(response.body()!!)
                } else {
                    DataResult.Error(response.message())
                }
            } catch (exception: Exception) {
                DataResult.Error(
                    exception.message ?: ""
                )
            }
        }
    }
}