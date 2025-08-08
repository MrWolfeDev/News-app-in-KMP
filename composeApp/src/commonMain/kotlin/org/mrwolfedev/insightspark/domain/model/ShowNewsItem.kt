package org.mrwolfedev.insightspark.domain.model

data class ShowNewsItem(
    val name: String,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
)