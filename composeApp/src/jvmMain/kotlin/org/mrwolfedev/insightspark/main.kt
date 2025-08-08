package org.mrwolfedev.insightspark

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import org.mrwolfedev.insightspark.di.presentationModule
import org.mrwolfedev.insightspark.di.sharedModules
import org.mrwolfedev.insightspark.navigation.AppNavigation

fun main() = application {
    startKoin {
        modules(
            sharedModules + listOf(presentationModule())
        )
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Insight Spark",
    ) {
        AppNavigation()
    }
}