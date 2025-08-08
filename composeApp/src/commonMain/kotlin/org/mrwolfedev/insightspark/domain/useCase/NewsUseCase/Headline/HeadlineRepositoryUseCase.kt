package com.wolf.news.domain.useCase.NewsUseCase.Headline

import org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.Headline.GetNewsHeadlineDataByCategoryUseCase
import org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.Headline.GetNewsHeadlineDataByCountryUseCase


class HeadlineRepositoryUseCase(
    val getByCountry: GetNewsHeadlineDataByCountryUseCase,
    val getByCategory: GetNewsHeadlineDataByCategoryUseCase

)


