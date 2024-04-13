package com.example.movieapp.navigation

import androidx.navigation.NavController
import androidx.navigation.createGraph

fun mainNavigationController(
    navController: NavController,
    graphStartDestination: String
) =
    navController.createGraph(
        startDestination = graphStartDestination
    ) {
        appNavigationGraph()
    }
