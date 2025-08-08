package org.mrwolfedev.insightspark.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.mrwolfedev.insightspark.data.remote.reposImplementation.NewsRepositoryImpl.HeadlineRepositoryImpl
import org.mrwolfedev.insightspark.data.services.NewsAPIServices
import org.mrwolfedev.insightspark.domain.repository.News.HeadlineRepository
import org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.Headline.GetNewsHeadlineDataByCategoryUseCase
import org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.Headline.GetNewsHeadlineDataByCountryUseCase
import com.wolf.news.domain.useCase.NewsUseCase.Headline.HeadlineRepositoryUseCase
import org.koin.dsl.module
import org.koin.core.module.Module
import org.mrwolfedev.insightspark.data.remote.reposImplementation.NewsRepositoryImpl.NewsRepositoryImpl
import org.mrwolfedev.insightspark.domain.repository.News.NewsRepository
import org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.News.GetNewsDataByTopicUseCase
import org.mrwolfedev.insightspark.domain.useCase.NewsUseCase.News.NewsRepositoryUseCase
import org.mrwolfedev.insightspark.presentation.NewsViewModel.NewsViewModel
val networkModule = module {
    single { NewsAPIServices(apiKey = "4bcb23f7dc544a22917413ad24bc77fc") }
}

val repositoryModule = module {
    single<HeadlineRepository> { HeadlineRepositoryImpl(get()) }
    single<NewsRepository>{ NewsRepositoryImpl(get()) }
}

val useCaseModule = module {
    singleOf(::GetNewsHeadlineDataByCountryUseCase)
    singleOf(::GetNewsHeadlineDataByCategoryUseCase)
    singleOf(::HeadlineRepositoryUseCase)
    singleOf(::GetNewsDataByTopicUseCase)
    singleOf(::NewsRepositoryUseCase)


}

// All common modules
val sharedModules = listOf(
    networkModule,
    repositoryModule,
    useCaseModule,
    presentationModule()
)


fun presentationModule(): Module = module {
    // Since in Compose you use koinInject(), register it as a singleton (or factory if you prefer):
    single { NewsViewModel(get()) }
}