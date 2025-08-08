package org.mrwolfedev.insightspark.presentation.Content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem
import org.mrwolfedev.insightspark.presentation.component.HeadingJVM
import org.mrwolfedev.insightspark.presentation.component.rememberDeviceInfo

@Composable
fun Content(
    newsItem: ShowNewsItem,
    onBackClick: () -> Unit = {}
) {
    val deviceInfo = rememberDeviceInfo()
    val scrollState = rememberScrollState()

    // Add debug log to see if Content is receiving the callback
    println("DEBUG: Content composable loaded with newsItem: ${newsItem.title}")
    println("DEBUG: onBackClick callback received: ${onBackClick != {}}")

    Box(
        modifier = Modifier
            .size(
                height = deviceInfo.screenHeight.dp,
                width = deviceInfo.screenWidth.dp
            )
            .background(color = Color(deviceInfo.backgroundColor))
    ) {
        // Scrollable content - Make sure it doesn't overlap the button area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    top = (deviceInfo.screenHeight * 0.15).dp, // Start below header and button
                    start = (deviceInfo.screenWidth * 0.05).dp,
                    end = (deviceInfo.screenWidth * 0.05).dp,
                    bottom = (deviceInfo.screenHeight * 0.02).dp
                )
        ) {
            // Author
            Text(
                text = if (newsItem.author.isNullOrEmpty()) {
                    "Author: Unknown"
                } else {
                    "Author: ${newsItem.author}"
                },
                color = Color(deviceInfo.contentColor),
                fontSize = (deviceInfo.screenWidth * 0.010).sp,
                modifier = Modifier.padding(bottom = (deviceInfo.screenHeight * 0.02).dp)
            )

            // Published date
            Text(
                text = if (newsItem.publishedAt.isNullOrEmpty()) {
                    "Published: Unknown"
                } else {
                    "Published: ${newsItem.publishedAt}"
                },
                color = Color(deviceInfo.contentColor),
                fontSize = (deviceInfo.screenWidth * 0.010).sp,
                modifier = Modifier.padding(bottom = (deviceInfo.screenHeight * 0.02).dp)
            )

            // Title
            Text(
                text = if (newsItem.title.isNullOrEmpty()) {
                    "Title: Unknown"
                } else {
                    newsItem.title
                },
                color = Color(deviceInfo.contentColor),
                fontWeight = FontWeight.Bold,
                fontSize = (deviceInfo.screenWidth * 0.012).sp,
                modifier = Modifier.padding(bottom = (deviceInfo.screenHeight * 0.02).dp)
            )

            // Description
            if (!newsItem.description.isNullOrEmpty()) {
                Text(
                    text = "Description:",
                    color = Color(deviceInfo.contentColor),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = (deviceInfo.screenWidth * 0.010).sp,
                    modifier = Modifier.padding(bottom = (deviceInfo.screenHeight * 0.01).dp)
                )

                Text(
                    text = newsItem.description,
                    color = Color(deviceInfo.contentColor),
                    fontWeight = FontWeight.Normal,
                    fontSize = (deviceInfo.screenWidth * 0.009).sp,
                    lineHeight = (deviceInfo.screenWidth * 0.012).sp,
                    modifier = Modifier.padding(bottom = (deviceInfo.screenHeight * 0.02).dp)
                )
            }

            // Divider
            HorizontalDivider(
                color = Color(deviceInfo.contentColor).copy(alpha = 0.3f),
                thickness = (deviceInfo.screenWidth * 0.001).dp,
                modifier = Modifier.padding(vertical = (deviceInfo.screenHeight * 0.02).dp)
            )

            // Content
            Text(
                text = "Content:",
                color = Color(deviceInfo.contentColor),
                fontWeight = FontWeight.SemiBold,
                fontSize = (deviceInfo.screenWidth * 0.010).sp,
                modifier = Modifier.padding(bottom = (deviceInfo.screenHeight * 0.01).dp)
            )

            Text(
                text = if (newsItem.content.isNullOrEmpty()) {
                    "No content available"
                } else {
                    newsItem.content
                },
                color = Color(deviceInfo.contentColor),
                fontWeight = FontWeight.Normal,
                fontSize = (deviceInfo.screenWidth * 0.009).sp,
                lineHeight = (deviceInfo.screenWidth * 0.012).sp,
                modifier = Modifier.padding(bottom = (deviceInfo.screenHeight * 0.02).dp)
            )

            // URL section (optional)
            if (!newsItem.url.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height((deviceInfo.screenHeight * 0.02).dp))

                Text(
                    text = "Source URL:",
                    color = Color(deviceInfo.contentColor),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = (deviceInfo.screenWidth * 0.010).sp,
                    modifier = Modifier.padding(bottom = (deviceInfo.screenHeight * 0.01).dp)
                )

                Text(
                    text = newsItem.url,
                    color = Color(0xFF2196F3), // Blue color for URL
                    fontWeight = FontWeight.Normal,
                    fontSize = (deviceInfo.screenWidth * 0.008).sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Fixed header that doesn't scroll - PLACED AFTER scrollable content so it's on top
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(
                    top = (deviceInfo.screenHeight * 0.05).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp,
                    end = (deviceInfo.screenWidth * 0.05).dp
                )
        ) {
            HeadingJVM(Heading = "Insight Spark")
        }

        // BACK BUTTON - PLACED LAST SO IT'S ON TOP OF EVERYTHING
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = (deviceInfo.screenHeight * 0.08).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp
                )
                .size(60.dp) // Fixed large size
                .background(Color.Red.copy(alpha = 0.8f)) // BRIGHT RED
                .clickable {
                    println("🔥🔥🔥 BACK BUTTON CLICKED!!! 🔥🔥🔥")
                    onBackClick()
                    println("🔥🔥🔥 onBackClick() CALLED!!! 🔥🔥🔥")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "BACK",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}