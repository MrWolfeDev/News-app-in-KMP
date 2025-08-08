package org.mrwolfedev.insightspark.presentation.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mrwolfedev.insightspark.presentation.component.CategoryRow
import org.mrwolfedev.insightspark.presentation.component.Heading
import org.mrwolfedev.insightspark.presentation.component.SearchBar
import org.mrwolfedev.insightspark.presentation.component.ShowNewsSmall
import org.mrwolfedev.insightspark.presentation.component.rememberDeviceInfo


val sampleNewsItems = listOf(
    ShowNewsItem(
        title = "Breaking: Major Climate Summit Reaches Historic Agreement on Carbon Emissions",
        sourceName = "Global News",
        imageUrl = "https://images.unsplash.com/photo-1569163139394-de4e4f43e4e5?w=800&h=600&fit=crop",
        sourceLogoUrl = "https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=100&h=100&fit=crop&crop=faces",
        badgeColor = 0xFF1976D2
    ),
    ShowNewsItem(
        title = "Tech Giants Announce Revolutionary AI Partnership for Healthcare Innovation",
        sourceName = "TechToday",
        imageUrl = "https://images.unsplash.com/photo-1576091160399-112ba8d25d1f?w=800&h=600&fit=crop",
        sourceLogoUrl = "https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=100&h=100&fit=crop&crop=faces",
        badgeColor = 0xFF00ACC1
    ),
    ShowNewsItem(
        title = "Olympic Games 2024: Record-Breaking Performances Captivate Global Audience",
        sourceName = "Sports Central",
        imageUrl = "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?w=800&h=600&fit=crop",
        sourceLogoUrl = "https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=100&h=100&fit=crop&crop=faces",
        badgeColor = 0xFFFF6F00
    ),
    ShowNewsItem(
        title = "Breakthrough Discovery: Scientists Find Potential Cure for Rare Genetic Disease",
        sourceName = "Medical Times",
        imageUrl = "https://images.unsplash.com/photo-1559757148-5c350d0d3c56?w=800&h=600&fit=crop",
        sourceLogoUrl = "https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=100&h=100&fit=crop&crop=faces",
        badgeColor = 0xFF388E3C
    ),
    ShowNewsItem(
        title = "Global Economy Shows Strong Recovery Signs as Markets Reach New Heights",
        sourceName = "Financial Daily",
        imageUrl = "https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?w=800&h=600&fit=crop",
        sourceLogoUrl = "https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=100&h=100&fit=crop&crop=faces",
        badgeColor = 0xFF7B1FA2
    ),
    ShowNewsItem(
        title = "Space Exploration Milestone: First Human Mission to Mars Successfully Launches",
        sourceName = "Space Explorer",
        imageUrl = "https://images.unsplash.com/photo-1446776877081-d282a0f896e2?w=800&h=600&fit=crop",
        sourceLogoUrl = "https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=100&h=100&fit=crop&crop=faces",
        badgeColor = 0xFFD32F2F
    ),
    ShowNewsItem(
        title = "Education Revolution: Virtual Reality Transforms Learning Experience Worldwide",
        sourceName = "EduNews",
        imageUrl = "https://images.unsplash.com/photo-1593508512255-86ab42a8e620?w=800&h=600&fit=crop",
        sourceLogoUrl = "https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=100&h=100&fit=crop&crop=faces",
        badgeColor = 0xFF5E35B1
    ),
    ShowNewsItem(
        title = "Environmental Victory: Largest Ocean Cleanup Project Removes 50,000 Tons of Plastic",
        sourceName = "Green World",
        imageUrl = "https://images.unsplash.com/photo-1583212292454-1fe6229603b7?w=800&h=600&fit=crop",
        sourceLogoUrl = "https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=100&h=100&fit=crop&crop=faces",
        badgeColor = 0xFF43A047
    )
)
@Preview(showSystemUi = true)

@Composable
fun Home() {
    val deviceInfo = rememberDeviceInfo()
    var searchQuery by remember { mutableStateOf("") }


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
            modifier = Modifier.padding(start = (deviceInfo.screenWidth * 0.05).dp,
                end = (deviceInfo.screenWidth* 0.05).dp,
                top=(deviceInfo.screenWidth*1.3).dp)
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier.align(Alignment.BottomCenter).size(
                width = (deviceInfo.screenWidth).dp,
                height = (deviceInfo.screenHeight * 0.3499999).dp
            ),
        ) { ShowNewsSmall(newsItems = sampleNewsItems) }

    }
}