package com.example.movieapp.presentation.details.model

import com.example.movieapp.data.remote.model.MovieDetail
import com.example.movieapp.presentation.base.ViewState

data class DetailsViewState(
    val detailsUiState: DetailsUiState = DetailsUiState.Loading,
    val uiDetails: List<UiModel> = listOf()
) : ViewState


sealed class DetailsUiState {
    data object Loading : DetailsUiState()
    data object Error : DetailsUiState()
    data class Success(val movieDetail: MovieDetail? = null) : DetailsUiState()
}