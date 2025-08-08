package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import insightspark.composeapp.generated.resources.Res
import insightspark.composeapp.generated.resources.search

import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchBar(
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onSearch: () -> Unit = {},
    placeholder: String = "Search"
) {
    val deviceInfo = rememberDeviceInfo()

    Box(
        modifier = Modifier
            .size(
                width = deviceInfo.screenWidth.dp,
                height = deviceInfo.screenHeight.dp
            )
    ) {
        // Custom Search TextField using BasicTextField
        val textFieldHeight = (deviceInfo.screenHeight * 0.07).dp
        val fontSize = (deviceInfo.screenWidth * 0.040).sp

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(
                    top = (deviceInfo.screenHeight * 0.14).dp,
                    start = (deviceInfo.screenWidth * 0.05).dp,
                    end = (deviceInfo.screenWidth * 0.05).dp
                )
                .height(textFieldHeight)
                .fillMaxWidth()
                .border(
                    width = (deviceInfo.screenWidth * 0.003).dp,
                    color = Color(deviceInfo.contentColor),
                    shape = RoundedCornerShape((deviceInfo.screenWidth * 0.01).dp)
                )
                .padding(horizontal = (deviceInfo.screenWidth * 0.03).dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search Icon
                Icon(
                    painter = painterResource(Res.drawable.search),
                    contentDescription = "search icon",
                    tint = if (searchQuery.isEmpty()) Color.Gray else Color(deviceInfo.contentColor),
                    modifier = Modifier
                        .size((deviceInfo.screenWidth * 0.06).dp)
                        .padding(end = (deviceInfo.screenWidth * 0.02).dp)
                )

                // TextField Container
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    // Placeholder Text
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = fontSize,
                            color = Color.Gray
                        )
                    }

                    // BasicTextField
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
                        modifier = Modifier.fillMaxSize(),
                        textStyle = TextStyle(
                            fontSize = fontSize,
                            color = Color(deviceInfo.contentColor),
                            textAlign = TextAlign.Start,
                            lineHeight = fontSize,
                        ),
                        singleLine = true,
                        cursorBrush = SolidColor(Color(0xFF77A5FF)),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = { onSearch() }
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                            }
                        }
                    )
                }
            }
        }
    }
}
