package com.example.movieapp.presentation.details.model

import com.example.movieapp.presentation.base.ViewEvent

sealed class DetailsEvent : ViewEvent {
    data class OnScreenLoad(val itemData: String) : DetailsEvent()
}