package org.mrwolfedev.insightspark.presentation.component.Data

data class DeviceInfo(
    val screenWidth: Int,
    val screenHeight: Int,
    val backgroundColor: Long, // ARGB
    val contentColor: Long,
    val recontentColor: Long
)