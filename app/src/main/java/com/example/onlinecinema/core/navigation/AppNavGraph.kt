package com.example.onlinecinema.core.navigation

import android.util.Log
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
            onMovieClick = {
                //TODO: replace with navController.navigate(...)
                Log.d("AppNavGraph", "On movie click")
            }
        )
    }

}