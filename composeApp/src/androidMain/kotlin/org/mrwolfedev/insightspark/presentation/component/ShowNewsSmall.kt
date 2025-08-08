package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun ShowNewsSmall(
    newsItems: List<ShowNewsItem>,
    modifier: Modifier = Modifier
) {
    val deviceInfo = rememberDeviceInfo()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = (deviceInfo.screenWidth * 0.03).dp),
        verticalArrangement = Arrangement.spacedBy((deviceInfo.screenHeight * 0.015).dp)

    ) {
        items(newsItems.size) { index ->
            val item = newsItems[index]
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Main Image
                KamelImage(
                    resource = asyncPainterResource(data = item.imageUrl),
                    contentDescription = item.title,
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
                    onLoading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(alpha = 0.3f))
                        )
                    },
                    onFailure = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(alpha = 0.5f))
                        )
                    }
                )

                // Source Logo Badge
                KamelImage(
                    resource = asyncPainterResource(data = item.sourceLogoUrl ?: ""),
                    contentDescription = item.sourceName,
                    modifier = Modifier
                        .padding(
                            start = (deviceInfo.screenWidth * 0.27).dp,
                            top = (deviceInfo.screenHeight * 0.07).dp,
                            bottom = (deviceInfo.screenHeight * 0.01).dp
                        )
                        .align(Alignment.TopStart)
                        .size((deviceInfo.screenWidth * 0.07).dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    onLoading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(alpha = 0.3f), CircleShape)
                        )
                    },
                    onFailure = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(item.badgeColor), CircleShape)
                        )
                    }
                )

                // Title Text
                Text(
                    text = item.title,
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