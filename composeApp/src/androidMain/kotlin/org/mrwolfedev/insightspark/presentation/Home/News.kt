package org.mrwolfedev.insightspark.presentation.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import org.mrwolfedev.insightspark.domain.util.Result
import org.mrwolfedev.insightspark.presentation.component.categoryColors
import org.mrwolfedev.insightspark.presentation.component.rememberDeviceInfo
import kotlin.collections.List


@Composable
fun ShowNewsSmall(
    result: Result<List<ShowNewsItem>>,
    selectedCategory: String,
    onNewsItemClick: (ShowNewsItem) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val deviceInfo = rememberDeviceInfo()
    val accentColor = categoryColors[selectedCategory] ?: Color(0xFFFFB900)

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
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = (deviceInfo.screenWidth * 0.03).dp),
                verticalArrangement = Arrangement.spacedBy((deviceInfo.screenHeight * 0.015).dp)
            ) {
                items(newsItems.size) { index ->
                    val item = newsItems[index]
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onNewsItemClick(item) // Fixed: use 'item' instead of 'newsItem'
                            }
                    ) {
                        // Main Image
                        KamelImage(
                            resource = {
                                if (item.urlToImage == null) // Fixed: use 'item' instead of 'newsItem'
                                    asyncPainterResource(data = Res.drawable.nothumbl)
                                else
                                    asyncPainterResource(data = item.urlToImage)
                            },
                            contentDescription = item.title, // Fixed: use 'item' instead of 'newsItem'
                            modifier = Modifier
                                .padding(
                                    start = (deviceInfo.screenWidth * 0.05).dp,
                                    top = (deviceInfo.screenHeight * 0.01).dp,
                                    bottom = (deviceInfo.screenHeight * 0.01).dp
                                )
                                .align(Alignment.TopStart)
                                .size((deviceInfo.screenWidth * 0.2).dp)
                                .clip(RoundedCornerShape((deviceInfo.screenWidth * 0.02).dp)),
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

                        // Source Logo Badge
                        Box(
                            modifier = Modifier
                                .padding(
                                    start = (deviceInfo.screenWidth * 0.27).dp,
                                    top = (deviceInfo.screenHeight * 0.07).dp,
                                    bottom = (deviceInfo.screenHeight * 0.01).dp
                                )
                                .clip(CircleShape)
                                .align(Alignment.TopStart)
                                .size((deviceInfo.screenWidth * 0.07).dp)
                                .background(accentColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.name.take(1).uppercase(), // Fixed: use 'item' instead of 'newsItem'
                                fontSize = (deviceInfo.screenWidth * 0.008).sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Title Text - Fixed: Moved outside the Source Logo Badge Box
                        item.title?.let {
                            Text(
                                text = it, // Fixed: use 'item.title' instead of 'newsItem.name'
                                color = Color(deviceInfo.contentColor),
                                fontWeight = FontWeight.Normal,
                                fontSize = (deviceInfo.screenWidth * 0.045).sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(
                                        top = (deviceInfo.screenHeight * 0.01).dp,
                                        start = (deviceInfo.screenWidth * 0.28).dp,
                                        end = (deviceInfo.screenWidth * 0.07).dp
                                    )
                            )
                        }
                    }
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