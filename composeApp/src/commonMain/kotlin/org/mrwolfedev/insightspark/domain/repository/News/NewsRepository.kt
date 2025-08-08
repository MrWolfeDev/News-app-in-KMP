package org.mrwolfedev.insightspark.domain.repository.News

import org.mrwolfedev.insightspark.data.remote.dto.NewsDTO
import org.mrwolfedev.insightspark.domain.util.Result


interface NewsRepository {
    suspend fun getNewsByTopic(topic: String,sortBy: String): Result<NewsDTO>
}