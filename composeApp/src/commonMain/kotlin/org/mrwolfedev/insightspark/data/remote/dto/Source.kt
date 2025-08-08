package org.mrwolfedev.insightspark.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable

data class Source(
    val id: String?,
    val name: String?
)
