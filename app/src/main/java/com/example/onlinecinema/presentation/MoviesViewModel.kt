package com.example.onlinecinema.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinecinema.core.network.TypedResult
import com.example.onlinecinema.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    fun searchMovies() {
        viewModelScope.launch {
            when (val result = moviesRepository.searchMovies(INITIAL_PAGE, INITIAL_QUERY)) {
                is TypedResult.Success -> {
                    Log.d("MoviesViewModel", "Success")
                }
                is TypedResult.Error -> {
                    Log.d("MoviesViewModel", "Error: ${result.error.message}")
                }
            }
        }
    }
}

private const val INITIAL_PAGE = 1
private const val INITIAL_QUERY = ""