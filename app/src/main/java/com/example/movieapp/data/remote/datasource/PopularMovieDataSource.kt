package com.example.movieapp.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.data.remote.services.ApiServices
import com.example.networksdk.data.MovieNetworkSDK
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularMovieDataSource @Inject constructor(
    movieNetworkSDK: MovieNetworkSDK
): PagingSource<Int,Movie>() {
    private val songsApiService by lazy { movieNetworkSDK.retrofit.create(ApiServices::class.java) }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        val response = songsApiService.fetchPopularMovies(page)
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
}