package org.mrwolfedev.insightspark.presentation.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import n.composeapp.generated.resources.Res
import n.composeapp.generated.resources.nothumbl
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem
import org.mrwolfedev.insightspark.presentation.component.rememberDeviceInfo
import javax.swing.Box
@Composable
fun Trending(
    newsItems: List<ShowNewsItem>,
    onNewsItemClick: (ShowNewsItem) -> Unit = {} // Add click handler parameter
) {
    val deviceInfo = rememberDeviceInfo()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    if (newsItems.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No news available",
                color = Color(deviceInfo.contentColor),
                fontSize = (deviceInfo.screenWidth * 0.012).sp
            )
        }
        return
    }

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    coroutineScope.launch {
                        listState.scrollBy(-dragAmount)
                    }
                }
            }
            .padding(horizontal = (deviceInfo.screenWidth * 0.02).dp),
        horizontalArrangement = Arrangement.spacedBy((deviceInfo.screenWidth * 0.02).dp)
    ) {
        items(newsItems) { newsItem ->
            TrendingDesign(
                newsItem = newsItem,
                onNewsItemClick = onNewsItemClick
            )
        }
    }
}

@Composable
fun TrendingDesign(
    newsItem: ShowNewsItem,
    onNewsItemClick: (ShowNewsItem) -> Unit = {} // Add click handler parameter
) {
    val deviceInfo = rememberDeviceInfo()

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .width((deviceInfo.screenWidth * 0.2).dp)
            .clickable { onNewsItemClick(newsItem) } // Add clickable modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape((deviceInfo.screenWidth * 0.01).dp))
                .size(
                    height = (deviceInfo.screenWidth * 0.1).dp,
                    width = (deviceInfo.screenWidth * 0.2).dp
                )
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
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
        }

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

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = (deviceInfo.screenWidth * 0.009).dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size((deviceInfo.screenWidth * 0.02).dp)
                    .background(Color(0xFFFFB900)),
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
                modifier = Modifier.padding(
                    start = (deviceInfo.screenWidth * 0.01).dp,
                )
            )
        }
    }
}