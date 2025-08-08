package org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.Headline

import org.mrwolfedev.insightspark.domain.repository.News.HeadlineRepository


class GetNewsHeadlineDataByCategoryUseCase(
    private val repository: HeadlineRepository
) {
    suspend operator fun invoke(country: String,category: String) = repository.getHeadlinesByCategory(country,category)
}
