package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.mrwolfedev.insightspark.domain.util.Result
import org.mrwolfedev.insightspark.presentation.NewsViewModel.NewsViewModel

val categoryColors = mapOf(
    "general" to Color(0xFF4CAF50),
    "business" to Color(0xFFFF9800),
    "entertainment" to Color(0xFFE91E63),
    "health" to Color(0xFF009688),
    "science" to Color(0xFF673AB7),
    "sports" to Color(0xFFF44336),
    "technology" to Color(0xFF03A9F4)
)

@Composable
fun CategoryRowJVM(
    viewModel: NewsViewModel,
    modifier: Modifier = Modifier
) {
    val categoryState by viewModel.categoryNewsState.collectAsState()
    val deviceInfo = rememberDeviceInfo()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val selectedCategory = viewModel.selectedCategory

    val categories = listOf(
        "general", "business", "entertainment", "health",
        "science", "sports", "technology"
    )
    when (categoryState) {
        is Result.Loading -> {
            Text(
                text = "Loading...",
                modifier = Modifier.padding(8.dp)
            )
        }

        is Result.Success -> {
            val news = (categoryState as Result.Success).data
            Text(
                text = "Loaded ${news.size} news",
                color = Color.Green,
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
            )
        }

        is Result.Error -> {
            Text(
                text = "Error: ${(categoryState as Result.Error).message}",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Result.Idle -> {}

    }

    LazyRow(
        state = listState,
        modifier = modifier.pointerInput(Unit) {
            detectHorizontalDragGestures { change, dragAmount ->
                change.consume()
                coroutineScope.launch { listState.scrollBy(-dragAmount) }
            }
        },
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { category ->
            val boxColor = if (category == selectedCategory) {
                categoryColors[category] ?: Color.Gray
            } else {
                Color.LightGray
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape((deviceInfo.screenWidth * 0.01).dp))
                    .background(boxColor)
                    .padding(
                        horizontal = (deviceInfo.screenWidth * 0.015).dp,
                        vertical = (deviceInfo.screenWidth * 0.0085).dp
                    )
                    .clickable {
                        viewModel.onCategorySelected(category)
                        viewModel.getHeadlinesByCategory("us", category)
                    }
            ) {
                Text(
                    text = category,
                    color = if (category == selectedCategory) Color.White else Color.Black,
                    fontSize = (deviceInfo.screenWidth * 0.007).sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = 100.dp)
                )
            }
        }
    }
}
