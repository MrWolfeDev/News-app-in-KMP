package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import insightspark.composeapp.generated.resources.Res
import insightspark.composeapp.generated.resources.notification
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun Heading(
    Heading : String
){
    val deviceInfo = rememberDeviceInfo()

    Box(
        modifier = Modifier
            .size(
                width = deviceInfo.screenWidth.dp,
                height = deviceInfo.screenHeight.dp
            )
    ){
        Text(
            text = Heading,
            color = Color(0xFF1877F2),
            fontWeight = FontWeight.Medium,
            fontSize = (deviceInfo.screenWidth * 0.065).sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = (deviceInfo.screenHeight * 0.07).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp
                )
        )

        KamelImage(
            resource = asyncPainterResource(data = Res.drawable.notification),
            contentDescription = "notification",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = (deviceInfo.screenHeight * 0.06).dp,
                    end = (deviceInfo.screenWidth * 0.05).dp
                )
                .size((deviceInfo.screenWidth * 0.12).dp)
        )
    }
}