package com.example.movieapp.presentation.details

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.navigation.NavigationArguments
import com.example.movieapp.presentation.base.BaseComposeFragment
import com.example.movieapp.presentation.details.model.DetailsEvent
import com.example.movieapp.presentation.home.model.HomeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseComposeFragment<HomeEvent>() {
    private val viewModel: DetailsViewModel by viewModels()

    @Composable
    override fun Screen() {
        DetailsScreen(viewModel = viewModel, navController = findNavController())
    }

    override fun getArgs() {
        arguments?.getString(NavigationArguments.ITEM_DATA)?.let {
            viewModel.handleEvent(DetailsEvent.OnScreenLoad(it))
        }
    }
}