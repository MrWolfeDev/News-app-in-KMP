package org.mrwolfedev.insightspark.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable

data class NewsDTO(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article>
)