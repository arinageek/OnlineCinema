package com.example.onlinecinema.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.onlinecinema.domain.movies.models.SearchMovie
import com.example.onlinecinema.presentation.movies.view.MoviesView

const val mainScreenRoute = "main"

fun NavGraphBuilder.mainScreen(onMovieClick: (movie: SearchMovie) -> Unit) {
    composable(route = mainScreenRoute) {
        MoviesView(
            onMovieClick = onMovieClick,
        )
    }
}

fun NavHostController.navigateToMain() {
    navigate(mainScreenRoute)
}