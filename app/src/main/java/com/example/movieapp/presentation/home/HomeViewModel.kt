package com.example.movieapp.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.domain.usecase.FetchLatestMoviesUseCase
import com.example.movieapp.domain.usecase.FetchPopularMoviesUseCase
import com.example.movieapp.navigation.AppNavigationGraphRoute
import com.example.movieapp.navigation.NavigationState
import com.example.movieapp.presentation.base.BaseViewModel
import com.example.movieapp.presentation.home.model.HomeEvent
import com.example.movieapp.presentation.home.model.HomeViewState
import com.example.movieapp.utils.DataResult
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchLatestMoviesUseCase: FetchLatestMoviesUseCase,
    private val fetchPopularMoviesUseCase: FetchPopularMoviesUseCase
) : BaseViewModel<HomeEvent, HomeViewState>() {

    private var _latestMovies: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val latestMovies: StateFlow<PagingData<Movie>> = _latestMovies.asStateFlow()

    private var _popularMovies: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val popularMovies: StateFlow<PagingData<Movie>> = _popularMovies.asStateFlow()

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnScreenLoad -> {
                fetchLatestMovies()
                fetchPopularMovies()
            }

            is HomeEvent.OnMovieClick -> {
                navigate(
                    NavigationState.NavigationDestination(
                        AppNavigationGraphRoute.DetailsFragment,
                        argsValues = arrayOf(event.movie.id.toString())
                    )
                )
            }
        }
    }

    private fun fetchLatestMovies() {
        viewModelScope.launch {
            fetchLatestMoviesUseCase
                .invoke()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _latestMovies.value = it

                }
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            fetchPopularMoviesUseCase
                .invoke()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _popularMovies.value = it
                }
        }
    }

    override fun getInitialViewState(): HomeViewState {
        return HomeViewState()
    }
}