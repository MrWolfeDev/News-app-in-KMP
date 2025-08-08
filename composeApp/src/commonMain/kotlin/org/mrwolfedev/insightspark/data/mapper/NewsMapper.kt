package org.mrwolfedev.insightspark.data.mapper

import org.mrwolfedev.insightspark.data.remote.dto.NewsDTO
import org.mrwolfedev.insightspark.domain.model.News


fun NewsDTO.toDomain(): News {
    return News(
        articles = articles.map { it.toDomain() }
    )
}
