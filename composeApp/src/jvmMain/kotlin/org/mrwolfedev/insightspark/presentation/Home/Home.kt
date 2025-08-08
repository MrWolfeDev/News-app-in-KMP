package org.mrwolfedev.insightspark.presentation.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject
import org.mrwolfedev.insightspark.InternetStatusBox
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem
import org.mrwolfedev.insightspark.presentation.NewsViewModel.NewsViewModel
import org.mrwolfedev.insightspark.presentation.component.CategoryRowJVM
import org.mrwolfedev.insightspark.presentation.component.HeadingJVM
import org.mrwolfedev.insightspark.presentation.component.SearchBar
import org.mrwolfedev.insightspark.presentation.component.rememberDeviceInfo
@Composable
fun HomeJVM(
    viewModel: NewsViewModel = koinInject(),
    onNewsItemClick: (ShowNewsItem) -> Unit = {}
) {
    val deviceInfo = rememberDeviceInfo()
    var searchQuery by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

//    // Log UI state changes
//    LaunchedEffect(uiState) {
//        println("DEBUG: UI State changed - isLoading: ${uiState.isLoading}")
//        println("DEBUG: Trending news count: ${uiState.trendingNews?.size}")
//        println("DEBUG: Category news count: ${uiState.categoryNews?.size}")
//        println("DEBUG: Error: ${uiState.error}")
//    }

    // Load initial data
    LaunchedEffect(Unit) {
//        println("DEBUG: Loading initial data")
        // Load trending news (general news from US)
        viewModel.loadTrendingNews("us")
        // Load default category news
        viewModel.getHeadlinesByCategory("us", viewModel.selectedCategory)
    }

    Box(
        modifier = Modifier
            .size(width = deviceInfo.screenWidth.dp, height = deviceInfo.screenHeight.dp)
            .background(color = Color(deviceInfo.backgroundColor))
    ) {
        HeadingJVM(Heading = "Insight Spark")

        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            onSearch = {
                // Search logic here
            }
        )

        // Explore text
        Text(
            text = "Explore",
            color = Color(deviceInfo.contentColor),
            fontWeight = FontWeight.Medium,
            fontSize = (deviceInfo.screenWidth * 0.015).sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = (deviceInfo.screenHeight * 0.15).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp
                )
        )

        // Trending section - Pass click handler
        Box(
            modifier = Modifier
                .padding(
                    top = (deviceInfo.screenHeight * 0.20).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp,
                    end = (deviceInfo.screenWidth * 0.05).dp
                )
                .size(
                    width = deviceInfo.screenWidth.dp,
                    height = (deviceInfo.screenWidth * 0.2).dp
                ),
            contentAlignment = Alignment.Center
        ) {
            val trendingNewsToShow = uiState.trendingNews?.take(10) ?: emptyList()
//            println("DEBUG: Showing ${trendingNewsToShow.size} items in Trending")
            Trending(
                newsItems = trendingNewsToShow,
                onNewsItemClick = onNewsItemClick // Pass the click handler
            )
        }

        // Category title
        Text(
            text = "Category",
            color = Color(deviceInfo.contentColor),
            fontWeight = FontWeight.Medium,
            fontSize = (deviceInfo.screenWidth * 0.015).sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = (deviceInfo.screenHeight * 0.58).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp
                )
        )

        // Category Row
        CategoryRowJVM(
            viewModel = viewModel,
            modifier = Modifier
                .padding(
                    top = (deviceInfo.screenHeight * 0.575).dp,
                    start = (deviceInfo.screenWidth * 0.13).dp
                )
                .align(Alignment.TopStart)
        )

        // News list - Shows category-specific news with click handler
        Box(
            modifier = Modifier
                .padding(
                    top = (deviceInfo.screenHeight * 0.70).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp,
                    end = (deviceInfo.screenWidth * 0.05).dp
                )
                .size(
                    width = deviceInfo.screenWidth.dp,
                    height = (deviceInfo.screenWidth * 0.2).dp
                ),
            contentAlignment = Alignment.Center
        ) {
            NewsJVM(
                result = viewModel.categoryNewsState.collectAsState().value,
                selectedCategory = viewModel.selectedCategory,
                onNewsItemClick = onNewsItemClick,
            )


        }
    }
}