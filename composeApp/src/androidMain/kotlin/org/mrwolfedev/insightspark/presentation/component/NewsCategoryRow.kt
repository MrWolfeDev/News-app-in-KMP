package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    val deviceInfo = rememberDeviceInfo()
    val categories = listOf(
        "general", "business", "entertainment", "health",
        "science", "sports", "technology "
    )
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories.size) { index ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape((deviceInfo.screenWidth * 0.02).dp))
                    .background(Color.LightGray)
                    .padding(
                        horizontal = (deviceInfo.screenWidth * 0.02).dp,
                        vertical = (deviceInfo.screenWidth * 0.01).dp
                    )
            ) {
                Text(
                    text = categories[index],
                    color = Color.Black,
                    fontSize = (deviceInfo.screenWidth * 0.03).sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = 100.dp)
                )
            }
        }
    }
}