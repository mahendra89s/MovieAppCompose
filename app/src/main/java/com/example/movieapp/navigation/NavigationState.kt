package com.example.movieapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions

sealed class NavigationState : NavigationHandler {
    data object NavigateUp : NavigationState() {

        override fun navigate(navController: NavController) {
            navController.navigateUp()
        }
    }

    class NavigationDestination(
        private val navigationGraphRoute: AppNavigationGraphRoute,
        private val clearBackStack: Boolean = false,
        private vararg val argsValues: String
    ) : NavigationState(),
        NavigationDestinationBuilder by NavigationDestinationBuilderImpl(
            navigationGraphRoute.route,
            argsValues.toList()
        ) {

        override fun navigate(navController: NavController) {
            val navOptions = if (!clearBackStack) navOptions {} else navOptions {
                launchSingleTop = true
                restoreState = false
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                    inclusive = true
                }
            }
            navController.navigate(buildFullPath(), navOptions)
        }
    }

    data object EmptyNavigation : NavigationState() {
        override fun navigate(navController: NavController) {
        }
    }
}