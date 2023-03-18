package com.example.onlinecinema.presentation.movies.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.onlinecinema.R
import com.example.onlinecinema.domain.movies.models.SearchMovie
import com.example.onlinecinema.presentation.movies.viewmodel.MoviesViewModel
import com.example.onlinecinema.ui.theme.Gray400

@Preview
@Composable
fun MoviesViewPreview() {
    MoviesView(
        query = "",
        movies = listOf(
            SearchMovie(
                id = 1,
                title = "Hello there",
                image = "https://images.unsplash.com/photo-1561731216-c3a4d99437d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1064&q=80"
            ),
            SearchMovie(
                id = 1,
                title = "Welcome",
                image = "https://images.unsplash.com/photo-1561731216-c3a4d99437d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1064&q=80"
            ),
        ),
        onMovieClick = {},
        onNewQuery = {},
    )
}

@Composable
internal fun MoviesView(
    viewModel: MoviesViewModel = hiltViewModel(),
    onMovieClick: (movie: SearchMovie) -> Unit,
) {
    val state by viewModel.searchResult.collectAsStateWithLifecycle()
    MoviesView(
        query = state.query,
        movies = state.movies,
        onMovieClick = onMovieClick,
        onNewQuery = viewModel::onNewQuery
    )
}

@Composable
private fun MoviesView(
    query: String,
    movies: List<SearchMovie>,
    onMovieClick: (movie: SearchMovie) -> Unit,
    onNewQuery: (query: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.white))
            .padding(all = 8.dp)
            .fillMaxSize()
    ) {
        SearchView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            input = query,
            onSearchChanged = onNewQuery,
            startingView = {},
            trailingView = { FilterIcon() },
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(movies) { movie ->
                MovieView(movie, onMovieClick)
            }
        }
    }
}

@Composable
private fun FilterIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_filter_24),
        contentDescription = stringResource(R.string.filter_icon_content_description),
        colorFilter = ColorFilter.tint(color = Gray400)
    )
}

@Composable
private fun MovieView(
    movie: SearchMovie,
    onMovieClick: (movie: SearchMovie) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable(onClick = { onMovieClick(movie) }),
    ) {
        Surface(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
        ) {
            AsyncImage(
                model = movie.image,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.poster_image_content_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .height(200.dp)
            )
        }
        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@Composable
private fun SearchView(
    modifier: Modifier = Modifier,
    input: String,
    onSearchChanged: (newQuery: String) -> Unit,
    startingView: @Composable () -> Unit,
    trailingView: @Composable () -> Unit,
) {
    BasicTextField(
        value = input,
        singleLine = true,
        onValueChange = onSearchChanged,
        modifier = modifier.defaultMinSize(minHeight = 48.dp),
    ) { innerTextField ->
        Surface(
            shape = RoundedCornerShape(size = 12.dp),
            elevation = 8.dp,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                startingView()
                Box {
                    if (input.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_placeholder_text),
                            style = TextStyle(color = Gray400),
                        )
                    }
                    innerTextField()
                }
                trailingView()
            }
        }
    }

}