package org.mrwolfedev.insightspark.data.remote.reposImplementation.NewsRepositoryImpl//package com.wolf.news.Data.reposImplementation.NewsRepositoryImpl
//
//import com.wolf.news.Data.remote.dto.NewsDTO
//import com.wolf.news.Data.services.NewsAPIServices
//import com.wolf.news.domain.repository.News.NewsRepository
//import io.ktor.client.call.body
//import io.ktor.client.request.get
//import io.ktor.client.request.parameter
//import javax.inject.Inject
//import org.mrwolfedev.insightspark.domain.util.Result
//
//class NewsRepositoryImpl @Inject constructor(
//    private val apiServices: NewsAPIServices
//) : NewsRepository {
//    override suspend fun getNewsByTopic(topic: String): Result<NewsDTO> {
//        return try {
//            val response = apiServices.client.get("v2/everything") {
//                parameter("q", topic)
//                parameter("X-Api-Key", apiServices.apiKey)
//            }.body<NewsDTO>()
//            Result.Success(response)
//        } catch (e: Exception) {
//            Result.Error("Failed to fetch topic in news: ${e.localizedMessage}")
//        }
//    }
//
//    override suspend fun getNewsBySort(sortBy: String): Result<NewsDTO> {
//        return try {
//            val response = apiServices.client.get("v2/everything") {
//                parameter("sortBy", sortBy)
//                parameter("X-Api-Key", apiServices.apiKey)
//            }.body<NewsDTO>()
//            Result.Success(response)
//        } catch (e: Exception) {
//            Result.Error("Failed to fetch sortBy in news: ${e.localizedMessage}")
//        }
//    }
//
//    override suspend fun getNewsByLanguage(language: String?): Result<NewsDTO> {
//        return try {
//            val response = apiServices.client.get("v2/everything") {
//                parameter("language", language ?: "en")
//                parameter("X-Api-Key", apiServices.apiKey)
//            }.body<NewsDTO>()
//            Result.Success(response)
//        } catch (e: Exception) {
//            Result.Error("Failed to fetch language in news: ${e.localizedMessage}")
//        }
//    }
//}
//
