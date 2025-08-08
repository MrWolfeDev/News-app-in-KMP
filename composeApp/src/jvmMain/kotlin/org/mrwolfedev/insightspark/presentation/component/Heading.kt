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

import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import n.composeapp.generated.resources.Res
import n.composeapp.generated.resources.notification


@Composable
fun HeadingJVM(
    Heading : String,
    Color : Color=Color(0xFF1877F2)
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
            color = Color,
            fontWeight = FontWeight.Medium,
            fontSize = (deviceInfo.screenWidth * 0.020).sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = (deviceInfo.screenHeight * 0.04).dp,
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