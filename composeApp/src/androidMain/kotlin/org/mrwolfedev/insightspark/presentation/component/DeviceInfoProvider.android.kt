package org.mrwolfedev.insightspark.presentation.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import org.mrwolfedev.insightspark.presentation.component.Data.DeviceInfo

@Composable
actual fun getDeviceInfo(): DeviceInfo {
    val configuration = LocalConfiguration.current
    val isDark = isSystemInDarkTheme()

    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val backgroundColor = if (isDark) 0xFF000000 else 0xFFFFFFFF
    val contentColor = if (isDark) 0xFFFFFFFF else 0xFF000000
    val recontentColor = if (isDark) 0xFF000000 else 0xFFFFFFFF

    return DeviceInfo(
        screenWidth = screenWidth,
        screenHeight = screenHeight,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        recontentColor = recontentColor
    )}