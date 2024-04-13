package com.example.movieapp.presentation.home.model

import com.example.movieapp.presentation.base.ViewState

data class HomeViewState(
    val selectedBottomType : BottomNavigationState = BottomNavigationState.LATEST,
    val bottomTypeList : List<BottomNavigationState> = listOf(
        BottomNavigationState.LATEST,
        BottomNavigationState.POPULAR
    )
) : ViewState
