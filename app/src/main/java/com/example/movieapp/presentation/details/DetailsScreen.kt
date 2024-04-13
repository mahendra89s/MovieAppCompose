package com.example.movieapp.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.R
import com.example.movieapp.navigation.HandleNavigation
import com.example.movieapp.presentation.details.components.DetailsItemView
import com.example.movieapp.presentation.details.model.DetailsUiState
import com.example.movieapp.presentation.details.model.DetailsViewState
import com.example.movieapp.utils.random

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    navController: NavController
) {
    val state by viewModel.viewState.collectAsState()
    HandleNavigation(navigationFlow = viewModel.navigation) {
        it.navigate(navController)
    }
    DetailsView(
        viewState = state,
        onBackClick = {
            navController.navigateUp()
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsView(
    viewState: DetailsViewState,
    onBackClick: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
        ) {
            when (val uiState = viewState.detailsUiState) {
                is DetailsUiState.Success -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    ) {

                        Box {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.Black,
                                    )
                                    .align(Alignment.TopCenter)
                                    .height(250.dp),
                                painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500${uiState.movieDetail?.backdropPath}"),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .background(
                                        brush =
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Transparent,
                                                Color.Transparent,
                                                Color.Transparent,
                                                Color(0xFF000000)
                                            )
                                        )
                                    ).align(Alignment.BottomCenter),
                            )
                        }

                        Icon(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(10.dp)
                                .clickable {
                                    onBackClick()
                                }
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(10.dp),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )

                        Image(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .padding(top = 10.dp)
                                .height(200.dp)
                                .width(150.dp)
                                .background(
                                    color = Color.Transparent,
                                )
                                .clip(
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .align(Alignment.BottomStart),
                            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500${uiState.movieDetail?.posterPath}"),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        text = uiState.movieDetail?.originalTitle ?: "",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                        text = uiState.movieDetail?.tagline ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 30.dp, start = 20.dp, end = 20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Green)
                            .padding(5.dp),
                        text = uiState.movieDetail?.status ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        text = stringResource(id = R.string.genres),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                    ) {
                        uiState.movieDetail?.genres?.forEach {
                            Text(
                                modifier = Modifier
                                    .padding(end = 10.dp, top = 10.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.random())
                                    .padding(5.dp),
                                text = it.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        text = stringResource(id = R.string.overview),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
                        text = uiState.movieDetail?.overview ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )

                    for (index in 0..<viewState.uiDetails.size step 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {

                            DetailsItemView(
                                modifier = Modifier.weight(1f),
                                tile = viewState.uiDetails[index].label.asString(),
                                value = viewState.uiDetails[index].value
                            )
                            if (index + 1 < viewState.uiDetails.size) {
                                DetailsItemView(
                                    modifier = Modifier.weight(1f),
                                    tile = viewState.uiDetails[index + 1].label.asString(),
                                    value = viewState.uiDetails[index + 1].value
                                )
                            }

                        }
                    }

                }

                is DetailsUiState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }

                else -> {}
            }
        }

    }
}

