package com.example.movieapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.navigation.AppNavigationGraphRoute
import com.example.movieapp.navigation.mainNavigationController
import com.example.networksdk.data.MovieNetworkSDK

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject lateinit var movieNetworkSDK: MovieNetworkSDK


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigationGraph(AppNavigationGraphRoute.AppGraph.buildFullPath())
        movieNetworkSDK.invoke(this)
    }

    private fun setUpNavigationGraph(
        initialNavigationDestination: String
    ) {
        navController = getNavigationController()
        navController.graph = mainNavigationController(
            navController = navController,
            graphStartDestination = initialNavigationDestination
        )
    }

    private fun getNavigationController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostContainer.id) as NavHostFragment
        return navHostFragment.navController
    }
}
