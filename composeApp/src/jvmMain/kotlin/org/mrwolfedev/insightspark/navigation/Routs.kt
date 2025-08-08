package org.mrwolfedev.insightspark.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem
import org.mrwolfedev.insightspark.presentation.Content.Content
import org.mrwolfedev.insightspark.presentation.Home.HomeJVM

sealed class AppScreen : Screen {

    object Home : AppScreen() {
        @Composable
        override fun Content() {
            val navigator = LocalNavigator.currentOrThrow
            HomeJVM(
                onNewsItemClick = { newsItem ->
                    println("DEBUG: Navigating to content screen for: ${newsItem.title}")
                    navigator.push(ContentScreen(newsItem))
                }
            )
        }
    }

    data class ContentScreen(val newsItem: ShowNewsItem) : AppScreen() {
        @Composable
        override fun Content() {
            val navigator = LocalNavigator.currentOrThrow
            println("DEBUG: In ContentScreen for: ${newsItem.title}")

            Content(
                newsItem = newsItem,
                onBackClick = {
                    println("DEBUG: HARDCODED - Pushing Home screen!")
                    // Hardcoded: Push Home screen
                    navigator.push(Home)
                    println("DEBUG: HARDCODED - Pushed Home screen successfully")
                }
            )
        }
    }
}