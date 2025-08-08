package org.mrwolfedev.insightspark.presentation.component


import androidx.compose.runtime.Composable
import org.mrwolfedev.insightspark.presentation.component.Data.DeviceInfo

@Composable
expect fun getDeviceInfo(): DeviceInfo

@Composable
fun rememberDeviceInfo(): DeviceInfo {
    return getDeviceInfo()
}