package org.mrwolfedev.insightspark.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun AppNavigation() {
    Navigator(AppScreen.Home)
}
