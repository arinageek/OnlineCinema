package com.example.onlinecinema.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.onlinecinema.presentation.player.view.MoviePlayerView

const val playerRoute = "moviePlayer"

fun NavGraphBuilder.moviePlayer() {
    composable(
        route = "$playerRoute/{$movieIdArg}",
        arguments = listOf(
            navArgument(movieIdArg) {
                type = NavType.IntType
            }
        ),
    ) {
        MoviePlayerView()
    }
}

fun NavController.navigateToMoviePlayer(movieId: Int) {
    navigate("$playerRoute/$movieId")
}