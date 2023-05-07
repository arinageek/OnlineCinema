package com.example.onlinecinema.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = mainScreenRoute,
        modifier = modifier,
    ) {
        mainScreen(
            onMovieClick = { movie ->
                navController.navigateToMovieInfo(movie.id)
            }
        )
        movieInfo(
            onWatchClicked = { movieId ->
                navController.navigateToMoviePlayer(movieId)
            }
        )
        moviePlayer()
    }

}