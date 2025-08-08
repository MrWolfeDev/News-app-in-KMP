package org.mrwolfedev.insightspark.domain.repository.News

import org.mrwolfedev.insightspark.data.remote.dto.NewsDTO

import org.mrwolfedev.insightspark.domain.util.Result

interface HeadlineRepository {
    suspend fun getHeadlinesByCountry(country: String): Result<NewsDTO>

    suspend fun getHeadlinesByCategory(country: String,category: String): Result<NewsDTO>

}







