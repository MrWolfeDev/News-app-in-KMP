package org.mrwolfedev.insightspark.presentation.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import n.composeapp.generated.resources.Res
import n.composeapp.generated.resources.nothumbl
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem
import org.mrwolfedev.insightspark.presentation.component.categoryColors
import org.mrwolfedev.insightspark.presentation.component.rememberDeviceInfo
import org.mrwolfedev.insightspark.domain.util.Result.Loading
import org.mrwolfedev.insightspark.domain.util.Result

// 📁 Still inside Home.kt or wherever this file is
@Composable
fun NewsJVM(
    result: Result<List<ShowNewsItem>>,
    selectedCategory: String,
    onNewsItemClick: (ShowNewsItem) -> Unit = {}
) {
    val deviceInfo = rememberDeviceInfo()

    when (result) {
        is Result.Loading -> {
            Box(
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color(deviceInfo.contentColor)
                )
            }
        }

        is Result.Error -> {
            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Failed to load news",
                    color = Color.Red,
                    fontSize = (deviceInfo.screenWidth * 0.012).sp
                )
            }
        }

        is Result.Success -> {
            val newsItems = result.data
            if (newsItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No news available for $selectedCategory",
                        color = Color(deviceInfo.contentColor),
                        fontSize = (deviceInfo.screenWidth * 0.012).sp
                    )
                }
                return
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val groupedItems = newsItems.chunked(4)
                items(groupedItems) { rowItems ->
                    NewsRow(
                        newsItems = rowItems,
                        category = selectedCategory,
                        onNewsItemClick = onNewsItemClick
                    )
                }
            }
        }

        is Result.Idle -> {
            Box(
                modifier = Modifier.fillMaxWidth().height(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Waiting for data...",
                    color = Color.Gray,
                    fontSize = (deviceInfo.screenWidth * 0.012).sp
                )
            }
        }
    }
}

// ✅ Move these OUTSIDE News()
@Composable
fun NewsRow(
    newsItems: List<ShowNewsItem>,
    category: String,
    onNewsItemClick: (ShowNewsItem) -> Unit = {}
) {
    val deviceInfo = rememberDeviceInfo()

    Row(
        horizontalArrangement = Arrangement.spacedBy((deviceInfo.screenWidth * 0.02).dp),
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        newsItems.forEach { newsItem ->
            NewsDesign(
                newsItem = newsItem,
                category = category,
                modifier = Modifier.weight(1f),
                onNewsItemClick = onNewsItemClick
            )
        }

        repeat(4 - newsItems.size) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun NewsDesign(
    newsItem: ShowNewsItem,
    category: String,
    modifier: Modifier = Modifier,
    onNewsItemClick: (ShowNewsItem) -> Unit = {}
) {
    val deviceInfo = rememberDeviceInfo()
    val accentColor = categoryColors[category] ?: Color(0xFFFFB900)

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.clickable { onNewsItemClick(newsItem) }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape((deviceInfo.screenWidth * 0.01).dp))
                .size(
                    height = (deviceInfo.screenWidth * 0.1).dp,
                    width = (deviceInfo.screenWidth * 0.2).dp
                )
            // Removed the clickable from here since it's now on the parent Column
        ) {
            KamelImage(
                resource = {
                    if (newsItem.urlToImage == null)
                        asyncPainterResource(data = Res.drawable.nothumbl)
                    else
                        asyncPainterResource(data = newsItem.urlToImage)
                },
                contentDescription = newsItem.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                onLoading = { progress ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            progress = { progress },
                            modifier = Modifier.size(24.dp),
                            color = Color(deviceInfo.contentColor),
                            strokeWidth = 2.dp
                        )
                    }
                },
                onFailure = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📰 NO image",
                            fontSize = (deviceInfo.screenWidth * 0.015).sp,
                            color = Color.White
                        )
                    }
                }
            )
        }

        // Title
        newsItem.title?.let {
            Text(
                text = it,
                color = Color(deviceInfo.contentColor),
                fontWeight = FontWeight.Normal,
                fontSize = (deviceInfo.screenWidth * 0.01).sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = (deviceInfo.screenWidth * 0.009).dp)
            )
        }

        // Source info
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = (deviceInfo.screenWidth * 0.009).dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size((deviceInfo.screenWidth * 0.02).dp)
                    .background(accentColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = newsItem.name.take(1).uppercase(),
                    fontSize = (deviceInfo.screenWidth * 0.008).sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = newsItem.name,
                color = Color(deviceInfo.contentColor),
                fontWeight = FontWeight.Normal,
                fontSize = (deviceInfo.screenWidth * 0.0098).sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = (deviceInfo.screenWidth * 0.01).dp)
            )
        }
    }
}

