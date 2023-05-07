package com.example.onlinecinema.presentation.player.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.example.onlinecinema.presentation.player.viewmodel.PlayerViewModel

@Composable
internal fun MoviePlayerView(
    viewModel: PlayerViewModel = hiltViewModel(),
) {
    MoviePlayerView(viewModel.player)
}

@Composable
private fun MoviePlayerView(
    player: Player,
) {
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DisposableEffect(
            AndroidView(
                factory = { context ->
                    PlayerView(context).also {
                        it.player = player
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
            )
        ) {
            val observer = LifecycleEventObserver { owner, event ->
                when (event) {
                    Lifecycle.Event.ON_PAUSE -> {
                        player.pause()
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        player.play()
                    }
                    else -> {}
                }
            }
            val lifecycle = lifecycleOwner.value.lifecycle
            lifecycle.addObserver(observer)
            onDispose {
                lifecycle.removeObserver(observer)
            }
        }
    }

}