package org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.News

import org.mrwolfedev.insightspark.domain.repository.News.NewsRepository



class GetNewsDataByTopicUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(topic: String, sortBy: String) =
        repository.getNewsByTopic(topic, sortBy)
}