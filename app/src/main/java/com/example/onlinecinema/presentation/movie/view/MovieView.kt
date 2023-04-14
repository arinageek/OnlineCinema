package com.example.onlinecinema.presentation.movie.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.onlinecinema.R
import com.example.onlinecinema.presentation.movie.viewmodel.MovieInfoState
import com.example.onlinecinema.presentation.movie.viewmodel.MovieViewModel
import com.example.onlinecinema.ui.theme.Gray400
import com.example.onlinecinema.ui.theme.Green600

@Preview(
    showSystemUi = true,
)
@Composable
fun MovieViewPreview() {
    MovieView(
        onWatchClick = {},
        movieInfo = MovieInfoState(
            title = "Бэтмен",
            rating = 8.4f,
            views = "120k просмотров",
            year = 2020,
            genres = listOf("Комедия", "Боевик"),
            countries = listOf("США", "Канада"),
            image = "https://images.unsplash.com/photo-1561731216-c3a4d99437d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
            description = "Бэтмен отправляется в преступный мир Готэм-сити, когда убийца-садист оставляет за собой загадочные улики. По мере того, как улики начинают приближаться к дому и масштаб планов преступника становится ясным, он должен наладить новые отношения, разоблачить виновного и восстановить справедливость в отношении злоупотребления властью и коррупции, которые давно преследуют мегаполис."
        )
    )
}

@Composable
internal fun MovieView(
    viewModel: MovieViewModel = hiltViewModel(),
    onWatchClick: (movieId: Int) -> Unit,
) {
    val state by viewModel.movieInfo.collectAsStateWithLifecycle()
    MovieView(
        movieInfo = state,
        onWatchClick = onWatchClick,
    )
}

@Composable
fun MovieView(
    movieInfo: MovieInfoState,
    onWatchClick: (movieId: Int) -> Unit,
) {
    Column {
        AsyncImage(
            model = movieInfo.image,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(id = R.string.poster_image_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .height(300.dp)
        )
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .weight(weight = 1f),
        ) {
            Text(
                text = movieInfo.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
            ) {
                Text(
                    text = movieInfo.rating.toString(),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(CornerSize(16.dp)))
                        .background(color = Green600)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Text(
                    text = movieInfo.views,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier
                        .clip(RoundedCornerShape(CornerSize(16.dp)))
                        .background(color = Gray400)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            Text(
                text = "${movieInfo.year}, ${movieInfo.genres.joinToString()}",
            )
            Text(
                text = movieInfo.countries.joinToString(),
                modifier = Modifier.padding(horizontal = 0.dp, vertical = 8.dp)
            )
            Text(
                text = stringResource(R.string.movie_description_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = movieInfo.description,
                modifier = Modifier.padding(horizontal = 0.dp, vertical = 8.dp)
            )

        }
        Button(
            onClick = { onWatchClick(movieInfo.id) },
            shape = RoundedCornerShape(CornerSize(16.dp)),
            modifier = Modifier
                .padding(all = 16.dp)
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.watch_button_title))
        }
    }

}