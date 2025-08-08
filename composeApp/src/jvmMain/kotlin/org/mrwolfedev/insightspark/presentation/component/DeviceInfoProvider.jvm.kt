package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.mrwolfedev.insightspark.presentation.component.Data.DeviceInfo
import java.awt.GraphicsEnvironment
import java.util.prefs.Preferences

@Composable
actual fun getDeviceInfo(): DeviceInfo {


    return remember {
        val isDark = detectDarkMode()
        val backgroundColor = if (isDark) 0xFF000000 else 0xFFFFFFFF
        val contentColor = if (isDark) 0xFFFFFFFF else 0xFF000000
        val recontentColor = if (isDark) 0xFF000000 else 0xFFFFFFFF
        val screenSize = getScreenSize()

        DeviceInfo(
            screenWidth = screenSize.first,
            screenHeight = screenSize.second,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            recontentColor = recontentColor
        )
    }
}

private fun detectDarkMode(): Boolean {
    val osName = System.getProperty("os.name").lowercase()

    return when {
        osName.contains("mac") -> {
            try {
                val process = Runtime.getRuntime()
                    .exec(arrayOf("defaults", "read", "-g", "AppleInterfaceStyle"))
                val output = process.inputStream.bufferedReader().readText()
                output.trim().equals("Dark", ignoreCase = true)
            } catch (e: Exception) {
                false
            }
        }

        osName.contains("win") -> {
            try {
                val prefs = Preferences.userRoot()
                    .node("Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize")
                val value = prefs.getInt("AppsUseLightTheme", 1)
                value == 0
            } catch (e: Exception) {
                false
            }
        }

        osName.contains("nux") || osName.contains("nix") -> {
            try {
                val process =
                    Runtime.getRuntime().exec("gsettings get org.gnome.desktop.interface gtk-theme")
                val output = process.inputStream.bufferedReader().readText()
                output.contains("dark", ignoreCase = true)
            } catch (e: Exception) {
                false
            }
        }

        else -> false
    }
}

private fun getScreenSize(): Pair<Int, Int> {
    return try {
        val bounds = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .defaultScreenDevice.defaultConfiguration.bounds
        bounds.width to bounds.height
    } catch (e: Exception) {
        1920 to 1080 // Better default fallback
    }
}



