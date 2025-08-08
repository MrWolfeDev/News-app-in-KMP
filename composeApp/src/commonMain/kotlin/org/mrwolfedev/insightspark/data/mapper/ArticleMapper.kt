package org.mrwolfedev.insightspark.data.mapper

import org.mrwolfedev.insightspark.data.remote.dto.Article
import org.mrwolfedev.insightspark.domain.model.ShowNewsItem

fun Article.toDomain(): ShowNewsItem {
    println("DEBUG: Mapping article - title: $title, source.name: ${source.name}, url: $url")

    try {
        return ShowNewsItem(
            name = source.name ?: "Unknown Source",
            author = author ?: "Author name can not found",
            title = title ?: "No Title Available ",
            description = description,
            url = url ?: "NO url found",
            urlToImage = urlToImage,
            publishedAt = publishedAt ?: "No published date time found",
            content = content,
        )
    } catch (e: Exception) {
        println("DEBUG: Error in toDomain mapping: ${e.message}")
        throw e
    }
}