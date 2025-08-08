package org.mrwolfedev.insightspark.data.remote.reposImplementation.NewsRepositoryImpl

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import org.mrwolfedev.insightspark.data.remote.dto.NewsDTO
import org.mrwolfedev.insightspark.data.services.NewsAPIServices
import org.mrwolfedev.insightspark.domain.repository.News.HeadlineRepository
import org.mrwolfedev.insightspark.domain.util.Result

class HeadlineRepositoryImpl(
    private val apiServices: NewsAPIServices
) : HeadlineRepository {
    override suspend fun getHeadlinesByCountry(country: String): Result<NewsDTO> {
        return try {
            val response = apiServices.client.get("v2/top-headlines") {
                parameter("country", country)
                parameter("apiKey", apiServices.apiKey)
            }.body<NewsDTO>()
            Result.Success(response)
        } catch (e: RedirectResponseException) {
            // 3xx responses
            val body = e.response.bodyAsText()
            Result.Error("Redirect (${e.response.status}): $body")
        } catch (e: ClientRequestException) {
            // 4xx responses
            val body = e.response.bodyAsText()
            Result.Error("Client error (${e.response.status}): $body")
        } catch (e: ServerResponseException) {
            // 5xx responses
            val body = e.response.bodyAsText()
            Result.Error("Server error (${e.response.status}): $body")
        } catch (e: Exception) {
            // network error, timeout, serialization, etc.
            Result.Error("Unexpected error: ${e.message ?: e::class.simpleName}")
        }
    }


    override suspend fun getHeadlinesByCategory(country: String, category: String): Result<NewsDTO> {
           return try {
                val response = apiServices.client.get("v2/top-headlines") {
                    parameter("country", country)
                    parameter("category", category)
                    parameter("apiKey", apiServices.apiKey)

                }.body<NewsDTO>()
                Result.Success(response)
            } catch (e: RedirectResponseException) {
               // 3xx responses
               val body = e.response.bodyAsText()
               Result.Error("Redirect (${e.response.status}): $body")
           } catch (e: ClientRequestException) {
               // 4xx responses
               val body = e.response.bodyAsText()
               Result.Error("Client error (${e.response.status}): $body")
           } catch (e: ServerResponseException) {
               // 5xx responses
               val body = e.response.bodyAsText()
               Result.Error("Server error (${e.response.status}): $body")
           } catch (e: Exception) {
               // network error, timeout, serialization, etc.
               Result.Error("Unexpected error: ${e.message ?: e::class.simpleName}")
           }
        }
    }


