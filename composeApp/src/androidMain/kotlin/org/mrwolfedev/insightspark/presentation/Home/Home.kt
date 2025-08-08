package org.mrwolfedev.insightspark.presentation.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject
import org.mrwolfedev.insightspark.domain.model.News
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem
import org.mrwolfedev.insightspark.presentation.NewsViewModel.NewsViewModel
import org.mrwolfedev.insightspark.presentation.component.CategoryRow
import org.mrwolfedev.insightspark.presentation.component.Heading
import org.mrwolfedev.insightspark.presentation.component.SearchBar
import org.mrwolfedev.insightspark.presentation.component.rememberDeviceInfo


@Preview(showSystemUi = true)

@Composable
fun Home(
    viewModel: NewsViewModel = koinInject(),
    onNewsItemClick: (ShowNewsItem) -> Unit = {}
) {
    val deviceInfo = rememberDeviceInfo()
    var searchQuery by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()


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
            .size(
                width = deviceInfo.screenWidth.dp,
                height = deviceInfo.screenHeight.dp
            )
            .background(color = Color(deviceInfo.backgroundColor))
    ) {
        Heading(
            Heading = "News"
        )

        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            onSearch = {
                // Put your search logic here
                // For example: Log.d("Search", "Searching for: $searchQuery")
            }
        )
        Box(
            modifier =
                Modifier
                    .padding(
                        top = (deviceInfo.screenHeight * 0.25).dp,
                        start = (deviceInfo.screenWidth * 0.07).dp,
                        end = (deviceInfo.screenWidth * 0.07).dp
                    )
                    .clip(
                        RoundedCornerShape((deviceInfo.screenWidth * 0.02).dp)
                    )
                    .size(
                        height = (deviceInfo.screenWidth * 0.5).dp,
                        width = deviceInfo.screenWidth.dp
                    )
                    .background(Color(0xFF1877F2))

        ) {//*//Box image//*//}
        }
        Text(
            text = "Trending",
            color = Color(deviceInfo.contentColor),
            fontWeight = FontWeight.Medium,
            fontSize = (deviceInfo.screenWidth * 0.045).sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = (deviceInfo.screenHeight * 0.22).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp,
                    end = (deviceInfo.screenWidth * 0.05).dp

                )
        )

        Text(
            text = "Russian worship is shrinking on Black Sea",
            color = Color(deviceInfo.contentColor),
            fontWeight = FontWeight.Normal,
            fontSize = (deviceInfo.screenWidth * 0.045).sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = (deviceInfo.screenHeight * 0.50).dp,
                    start = (deviceInfo.screenWidth * 0.07).dp,
                    end = (deviceInfo.screenWidth * 0.07).dp
                )
        )
        Box(
            modifier =
                Modifier
                    .padding(
                        top = (deviceInfo.screenHeight * 0.5329).dp,
                        start = (deviceInfo.screenWidth * 0.07).dp,

                        )
                    .clip(shape = CircleShape)
                    .background(color = Color.Cyan)
                    .size((deviceInfo.screenWidth * 0.07).dp)
                    .align(Alignment.TopStart)
        ) {

        }

        Text(
            text = "BBC news",
            color = Color(deviceInfo.contentColor),
            fontWeight = FontWeight.Normal,
            fontSize = (deviceInfo.screenWidth * 0.045).sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = (deviceInfo.screenHeight * 0.5359).dp,
                    start = (deviceInfo.screenWidth * 0.15).dp,
                    end = (deviceInfo.screenWidth * 0.07).dp
                )
        )
        CategoryRow(
            viewModel = viewModel,
            modifier = Modifier
                .padding(
                    start = (deviceInfo.screenWidth * 0.05).dp,
                    end = (deviceInfo.screenWidth * 0.05).dp,
                    top = (deviceInfo.screenWidth * 1.3).dp
                )
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(
                    width = (deviceInfo.screenWidth).dp,
                    height = (deviceInfo.screenHeight * 0.3499999).dp
                ),
        ) {
            ShowNewsSmall(
                result = viewModel.categoryNewsState.collectAsState().value,
                selectedCategory = viewModel.selectedCategory,
                onNewsItemClick = onNewsItemClick,
                modifier = Modifier
            )
        }

    }
}