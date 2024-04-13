package com.example.movieapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.data.remote.model.Movie
import com.example.movieapp.navigation.HandleNavigation
import com.example.movieapp.presentation.home.components.MovieListView
import com.example.movieapp.presentation.home.model.BottomNavigationState
import com.example.movieapp.presentation.home.model.HomeEvent
import com.example.movieapp.presentation.home.model.HomeViewState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {
    val state by viewModel.viewState.collectAsState()
    val popularMovie = viewModel.popularMovies.collectAsLazyPagingItems()
    val latestMovie = viewModel.latestMovies.collectAsLazyPagingItems()
    LaunchedEffect(key1 = Unit) {
        viewModel.handleEvent(HomeEvent.OnScreenLoad)
    }

    HandleNavigation(navigationFlow = viewModel.navigation) {
        it.navigate(navController)
    }
    HomeView(
        uiState = state,
        popularMovie=popularMovie,
        latestMovie=latestMovie,
        onMovieClick = {
            viewModel.handleEvent(HomeEvent.OnMovieClick(it))
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView(
    uiState: HomeViewState,
    popularMovie: LazyPagingItems<Movie>,
    latestMovie: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = {
            2
        }
    )
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color(0xFF000000)
                    )
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HorizontalPager(
                        modifier = Modifier,
                        state = pagerState,
                        pageSpacing = 0.dp,
                        userScrollEnabled = true,
                        reverseLayout = false,
                        contentPadding = PaddingValues(0.dp),
                        beyondBoundsPageCount = 0,
                        pageSize = PageSize.Fill,
                        flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                        key = null,
                        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                            Orientation.Horizontal
                        ),
                        pageContent = {
                            when (val title = uiState.bottomTypeList[it]) {
                                BottomNavigationState.LATEST -> {
                                    MovieListView(
                                        modifier = Modifier,
                                        movie = latestMovie,
                                        title = title.type,
                                        onMovieClick = {
                                            onMovieClick(it)
                                        }
                                    )
                                }

                                else -> {
                                    MovieListView(
                                        modifier = Modifier,
                                        movie = popularMovie,
                                        title = title.type,
                                        onMovieClick = {
                                            onMovieClick(it)
                                        }
                                    )
                                }
                            }
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush =
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0xFF000000),
                                        Color(0xFF000000),
                                        Color(0xFF000000)
                                    )
                                )
                            ),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        (uiState.bottomTypeList.indices).forEach { index ->
                            BottomNavigationItem(
                                bottomNavigationState = uiState.bottomTypeList[index],
                                selectedIndex = pagerState.currentPage,
                                currentIndex = index,
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun BottomNavigationItem(
    bottomNavigationState: BottomNavigationState,
    selectedIndex: Int,
    currentIndex: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = bottomNavigationState.type,
            color = if (selectedIndex == currentIndex) Color.White else Color(0xFF999898),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Box(
            modifier = Modifier
                .size(5.dp)
                .background(
                    color = if (selectedIndex == currentIndex) Color.White else Color.Black
                )
                .clip(CircleShape)

        )
    }
}