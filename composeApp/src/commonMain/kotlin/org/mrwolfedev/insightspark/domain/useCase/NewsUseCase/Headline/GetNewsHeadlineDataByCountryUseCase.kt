package org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.Headline

import org.mrwolfedev.insightspark.domain.repository.News.HeadlineRepository


class GetNewsHeadlineDataByCountryUseCase(
    private val repository: HeadlineRepository
) {
    suspend operator fun invoke(country: String) = repository.getHeadlinesByCountry(country)
}