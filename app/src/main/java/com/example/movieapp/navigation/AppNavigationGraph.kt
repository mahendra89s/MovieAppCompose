package com.example.movieapp.navigation

import android.icu.text.MessagePattern.ArgType
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.example.movieapp.presentation.details.DetailsFragment
import com.example.movieapp.presentation.home.HomeFragment


fun NavGraphBuilder.appNavigationGraph() {
    navigation(
        startDestination = AppNavigationGraphRoute.HomeFragment.buildFullPath(),
        route = AppNavigationGraphRoute.AppGraph.buildFullPath()
    ){
        addDestination()
    }
}

fun NavGraphBuilder.addDestination(){
    fragment<HomeFragment>(
        route = AppNavigationGraphRoute.HomeFragment.buildFullPath()
    ){
        label = "HomeFragment"
    }

    fragment<DetailsFragment>(
        route = AppNavigationGraphRoute.DetailsFragment.buildFullPath()
    ){
        label = "DetailsFragment"
        argument(NavigationArguments.ITEM_DATA){
            type = NavType.StringType
            defaultValue = ""
        }
    }

}