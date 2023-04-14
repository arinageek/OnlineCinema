package com.example.onlinecinema.presentation.movie.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinecinema.core.navigation.movieIdArg
import com.example.onlinecinema.core.network.TypedResult
import com.example.onlinecinema.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MoviesRepository,
) : ViewModel() {

    private val _movieInfo = MutableStateFlow(MovieInfoState())
    val movieInfo = _movieInfo.asStateFlow()

    private val movieId: Int = checkNotNull(savedStateHandle[movieIdArg])

    init {
        loadMovieInfo(movieId)
    }

    private fun loadMovieInfo(movieId: Int) {
        viewModelScope.launch {
            when (val response = repository.loadMovieInfo(movieId)) {
                is TypedResult.Success -> {
                    _movieInfo.update { state ->
                        val res = response.result
                        state.copy(
                            id = res.id,
                            title = res.title,
                            description = res.description,
                            year = res.year,
                            image = res.image,
                            views = res.views,
                            rating = res.rating,
                            genres = res.genres,
                            countries = res.countries,
                            duration = res.duration,
                            isFavorite = res.isFavorite,
                        )
                    }
                }
                is TypedResult.Error -> {
                    Log.d("MovieViewModel", "Error: ${response.error.message}")
                }
            }
        }
    }

}