package com.example.onlinecinema.presentation.player.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.onlinecinema.core.navigation.movieIdArg
import com.example.onlinecinema.core.network.TypedResult
import com.example.onlinecinema.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository,
    val player: Player,
): ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[movieIdArg])

    init {
        initPlayer()
    }

    private fun initPlayer() {
        viewModelScope.launch {
            when(val response = moviesRepository.loadPlayer(movieId)) {
                is TypedResult.Success -> {
                    val mediaItem = MediaItem.fromUri(response.result.link)
                    with(player) {
                        setMediaItem(mediaItem)
                        prepare()
                        play()
                    }
                }
                is TypedResult.Error -> {
                    Log.d("PlayerViewModel", response.error.message)
                }
            }
        }
    }

}