package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import n.composeapp.generated.resources.unknown
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem

@Composable
fun Explore(
    newsItem: List<ShowNewsItem>,
    newsItems: ShowNewsItem,
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
        items(newsItem.size) { index ->
            val item = newsItem[index]

            Box(modifier = Modifier.fillMaxWidth()) {
                // Main image - Using Kamel for KMP
                KamelImage(
                    resource = {
                        if (newsItems.urlToImage == null)
                            asyncPainterResource(data = Res.drawable.nothumbl)
                        else
                            asyncPainterResource(data = newsItems.urlToImage)
                    },
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
                    onLoading = { progress ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                progress = { progress },
                                modifier = Modifier.size(24.dp),
                                color = Color.White,
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
                                text = "📰NO image",
                                fontSize = (deviceInfo.screenWidth * 0.015).sp,
                                color = Color.White
                            )
                        }
                    }
                )

                item.title?.let {
                    Text(
                        text = it,
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
                }
                Box(
                    modifier = Modifier      .padding(
                        top = (deviceInfo.screenWidth * 0.585f).dp,
                        start = (deviceInfo.screenWidth * 0.07).dp
                    )
                        .clip(CircleShape)
                        .size((deviceInfo.screenWidth * 0.07).dp)
                        .background(Color(0xFFFFB900)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = newsItems.name.take(1).uppercase(),
                        fontSize = (deviceInfo.screenWidth * 0.008).sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }


                // Source text
                Text(
                    text = newsItems.name,
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