package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import insightspark.composeapp.generated.resources.Res
import insightspark.composeapp.generated.resources.unknown
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ShowNewsBig(
    newsItems: List<ShowNewsItem>,
    modifier: Modifier = Modifier,
) {
    val deviceInfo = rememberDeviceInfo()

    LazyColumn(
        modifier = modifier
            .size(
                width = deviceInfo.screenWidth.dp,
                height = deviceInfo.screenHeight.dp
            )
            .padding(bottom = (deviceInfo.screenHeight * 0.03).dp),
        verticalArrangement = Arrangement.spacedBy((deviceInfo.screenHeight * 0.04).dp)
    ) {
        items(newsItems.size) { index ->
            val item = newsItems[index]

            Box(modifier = Modifier.fillMaxWidth()) {
                // Main image - Using Kamel for KMP
                KamelImage(
                    resource = asyncPainterResource(data = item.imageUrl),
                    contentDescription = item.title,
                    modifier = Modifier
                        .padding(
                            top = (deviceInfo.screenHeight * 0.01).dp,
                            start = (deviceInfo.screenWidth * 0.07).dp,
                            end = (deviceInfo.screenWidth * 0.07).dp
                        )
                        .clip(RoundedCornerShape((deviceInfo.screenWidth * 0.02).dp))
                        .size(
                            height = (deviceInfo.screenWidth * 0.5).dp,
                            width = (deviceInfo.screenWidth * 0.86).dp
                        )
                        .align(Alignment.TopStart),
                    contentScale = ContentScale.Crop,
                    onLoading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(alpha = 0.3f))
                        )
                    },
                    onFailure = {
                        // Error placeholder
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(alpha = 0.5f))
                        )
                    }
                )

                Text(
                    text = item.title,
                    color = Color(deviceInfo.contentColor),
                    fontWeight = FontWeight.Normal,
                    fontSize = (deviceInfo.screenWidth * 0.045).sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(
                            top = (deviceInfo.screenWidth * 0.53f).dp,
                            start = (deviceInfo.screenWidth * 0.07).dp,
                            end = (deviceInfo.screenWidth * 0.07).dp
                        )
                )

                KamelImage(
                    resource = if (item.sourceLogoUrl == null) asyncPainterResource(data = Res.drawable.unknown) else asyncPainterResource(
                        data = item.sourceLogoUrl
                    ), // Source logo URL
                    contentDescription = item.sourceName,
                    modifier = Modifier
                        .padding(
                            top = (deviceInfo.screenWidth * 0.585f).dp,
                            start = (deviceInfo.screenWidth * 0.07).dp
                        )
                        .clip(CircleShape)
                        .size((deviceInfo.screenWidth * 0.07).dp)
                        .align(Alignment.TopStart),
                    contentScale = ContentScale.Crop,
                    onLoading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(alpha = 0.3f), CircleShape)
                        )
                    },
                    onFailure = {
                        // Fallback to colored circle if logo fails
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(item.badgeColor), CircleShape)
                        )
                    },

                )

                // Source text
                Text(
                    text = item.sourceName,
                    color = Color(deviceInfo.contentColor),
                    fontWeight = FontWeight.Normal,
                    fontSize = (deviceInfo.screenWidth * 0.045).sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(
                            top = (deviceInfo.screenWidth * 0.589f).dp,
                            start = (deviceInfo.screenWidth * 0.15).dp,
                            end = (deviceInfo.screenWidth * 0.07).dp
                        )
                )
            }
        }
    }
}