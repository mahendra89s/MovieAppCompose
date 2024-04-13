package com.example.movieapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.data.remote.model.Movie

@Composable
fun MovieItemView(
    modifier: Modifier,
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {
    Card(
        modifier = modifier
            .width(70.dp)
            .clickable {
                onMovieClick(movie)
            }
            .padding(10.dp)
        ,
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = Color.Transparent,
                ).clip(
                    shape = RoundedCornerShape(10.dp)
                ),
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500${movie.posterPath}"),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            text = movie.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewMovieItemView() {
    MovieItemView(
        movie = Movie(
            adult = false,
            backdropPath = "",
            genreIds = listOf(1),
            id = 2,
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 2.0,
            posterPath = "/oPXzCV01ysDmnmpJOkiVqaZQ5QR.jpg",
            releaseDate = "12th august",
            title = "hello",
            video = true,
            voteAverage = 2.0,
            voteCount = 2
        ),
        modifier = Modifier,
        onMovieClick = {}
    )
}