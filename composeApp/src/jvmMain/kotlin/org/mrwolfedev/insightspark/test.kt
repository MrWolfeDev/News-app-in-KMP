package org.mrwolfedev.insightspark

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.ktor.client.request.get
import kotlinx.coroutines.launch
val httpClient = HttpClient()

suspend fun isInternetAvailable(): Boolean {
    return try {
        withContext(Dispatchers.Default) {
            val response: HttpResponse = httpClient.get("https://www.google.com")
            response.status.value in 200..399
        }
    } catch (e: Exception) {
        false
    }
}




@Composable
fun InternetStatusBox() {
    var isOnline by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            isOnline = isInternetAvailable()
            checked = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (!checked) Color.Cyan// Loading state
                else if (isOnline) Color.Green
                else Color.Red
            )
    )
}
