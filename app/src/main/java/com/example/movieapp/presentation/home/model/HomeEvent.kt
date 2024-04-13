package com.example.movieapp.presentation.home.model

import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.presentation.base.ViewEvent

sealed class HomeEvent : ViewEvent {
    data object OnScreenLoad : HomeEvent()
    data class OnMovieClick(val movie: Movie) : HomeEvent()
}