package com.example.onlinecinema.presentation.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinecinema.core.network.TypedResult
import com.example.onlinecinema.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _searchResult = MutableStateFlow(MoviesState())
    val searchResult = _searchResult.asStateFlow()

    private val queryFlow = MutableStateFlow(INITIAL_QUERY)

    init {
        viewModelScope.launch {
            queryFlow
                .sample(TEXT_ENTERED_DEBOUNCE_MILLIS)
                .mapLatest { searchMovies(INITIAL_PAGE, it) }
                .collect()
        }
    }

    fun onNewQuery(query: String) {
        _searchResult.update { state ->
            state.copy(query = query)
        }

        queryFlow.value = query
    }

    private fun searchMovies(
        page: Int = INITIAL_PAGE,
        query: String = INITIAL_QUERY,
    ) {
        viewModelScope.launch {
            when (val response = moviesRepository.searchMovies(page, query)) {
                is TypedResult.Success -> {
                    _searchResult.update { state ->
                        state.copy(
                            movies = response.result.movies,
                            page = response.result.page,
                            endReached = response.result.endReached,
                        )
                    }
                }
                is TypedResult.Error -> {
                    Log.d("MoviesViewModel", "Error: ${response.error.message}")
                }
            }
        }
    }
}

const val INITIAL_PAGE = 1
const val INITIAL_QUERY = ""
const val TEXT_ENTERED_DEBOUNCE_MILLIS = 3000L