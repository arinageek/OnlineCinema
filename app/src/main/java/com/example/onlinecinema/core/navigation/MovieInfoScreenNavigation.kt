package com.example.onlinecinema.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.onlinecinema.presentation.movie.view.MovieView

const val movieInfoRoute = "movieInfo"
const val movieIdArg = "movieId"

fun NavGraphBuilder.movieInfo(onWatchClicked: (movieId: Int) -> Unit) {
    composable(
        route = "$movieInfoRoute/{$movieIdArg}",
        arguments = listOf(
            navArgument(movieIdArg) {
                type = NavType.IntType
            }
        ),
    ) {
        MovieView(
            onWatchClick = onWatchClicked,
        )

    }
}

fun NavController.navigateToMovieInfo(movieId: Int) {
    navigate("$movieInfoRoute/$movieId")
}