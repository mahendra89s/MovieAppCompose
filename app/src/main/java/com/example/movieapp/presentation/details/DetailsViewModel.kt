package com.example.movieapp.presentation.details

import androidx.lifecycle.viewModelScope
import com.example.movieapp.R
import com.example.movieapp.data.remote.model.MovieDetail
import com.example.movieapp.domain.usecase.FetchMovieDetailsUseCase
import com.example.movieapp.presentation.base.BaseViewModel
import com.example.movieapp.presentation.details.model.DetailsEvent
import com.example.movieapp.presentation.details.model.DetailsUiState
import com.example.movieapp.presentation.details.model.DetailsViewState
import com.example.movieapp.presentation.details.model.UiModel
import com.example.movieapp.ui.theme.UiText
import com.example.movieapp.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
) : BaseViewModel<DetailsEvent, DetailsViewState>() {

    override fun handleEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.OnScreenLoad -> fetchMovieDetails(event.itemData)
        }
    }

    private fun fetchMovieDetails(id: String) {
        viewModelScope.launch {
            fetchMovieDetailsUseCase.invoke(id).collect {
                when (it) {
                    is DataResult.Success -> {
                        setState {
                            copy(
                                detailsUiState = DetailsUiState.Success(
                                    movieDetail = it.data
                                ),
                                uiDetails = prepareUiData(it.data)
                            )
                        }
                    }

                    is DataResult.Error -> {
                        setState {
                            copy(
                                detailsUiState = DetailsUiState.Error
                            )
                        }
                    }
                }
            }
        }
    }

    private fun prepareUiData(
        movieDetail: MovieDetail
    ): List<UiModel> {
        return listOf(
            UiModel(
                label = UiText.StringResourceType(
                    R.string.vote_average
                ),
                value = movieDetail.voteAverage.toString()
            ),
            UiModel(
                label = UiText.StringResourceType(
                    R.string.vote_count,
                ),
                value = movieDetail.voteCount.toString()
            ),
            UiModel(
                label = UiText.StringResourceType(
                    R.string.release_date,
                ),
                value = movieDetail.releaseDate
            ),
            UiModel(
                label = UiText.StringResourceType(
                    R.string.revenue,
                ),
                value = movieDetail.revenue.currencyFormat()
            ),
            UiModel(
                label = UiText.StringResourceType(
                    R.string.budget,
                ),
                value = movieDetail.budget.currencyFormat()
            ),
            UiModel(
                label = UiText.StringResourceType(
                    R.string.language,
                ),
                value = movieDetail.originalLanguage.getLanguageName()
            )
        )
    }

    private fun Int.currencyFormat(): String {
        val formatter = DecimalFormat("#,###")
        return "$ ${formatter.format(this)}"
    }
    private fun String.getLanguageName(): String{
        return Locale(this,"").displayLanguage
    }

    override fun getInitialViewState(): DetailsViewState {
        return DetailsViewState()
    }
}