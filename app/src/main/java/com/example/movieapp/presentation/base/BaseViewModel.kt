package com.example.movieapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.navigation.NavigationState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : ViewEvent, S : ViewState> : ViewModel() {

    private val _viewState = MutableStateFlow(getInitialViewState())
    val viewState: StateFlow<S> = _viewState

    private val _navigation = MutableSharedFlow<NavigationState>()
    val navigation: SharedFlow<NavigationState> = _navigation

    private val _events = MutableSharedFlow<E>()

    init {
        subscribeEvents()
    }

    fun navigate(navigationState: NavigationState) {
        viewModelScope.launch {
            _navigation.emit(navigationState)
        }
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            _events.collect {
                handleEvent(it)
            }
        }
    }

    abstract fun handleEvent(event: E)

    /**
     * Initial state of state flow for the ViewState
     */
    abstract fun getInitialViewState(): S

    protected fun setState(newState: S.() -> S) {
        _viewState.update { it.newState() }
    }
}
