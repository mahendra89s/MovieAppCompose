package com.example.movieapp.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.movieapp.data.remote.model.Movie

@Composable
fun MovieListView(
    modifier: Modifier,
    movie: LazyPagingItems<Movie>,
    title: String,
    onMovieClick: (Movie) -> Unit
) {
    Column {
        Text(
            modifier = Modifier.padding(top = 20.dp, start = 20.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.White
        )

        LazyVerticalStaggeredGrid(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = 20.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            columns = StaggeredGridCells.Fixed(2)
        ) {
            items(movie.itemCount) {
                movie[it]?.let { it1 ->
                    MovieItemView(
                        modifier = Modifier,
                        movie = it1,
                        onMovieClick = onMovieClick
                    )
                }

                if (it == movie.itemCount - 1) {
                    Spacer(modifier = Modifier.height(300.dp))
                }
            }

            movie.apply {
                when{
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Box(
                                Modifier.fillMaxWidth()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp).align(Alignment.TopCenter)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}