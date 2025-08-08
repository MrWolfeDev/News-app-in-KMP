package org.mrwolfedev.insightspark.data.remote.reposImplementation.NewsRepositoryImpl

import io.ktor.client.request.*
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mrwolfedev.insightspark.data.remote.dto.NewsDTO
import org.mrwolfedev.insightspark.data.services.NewsAPIServices
import org.mrwolfedev.insightspark.domain.repository.News.NewsRepository
import org.mrwolfedev.insightspark.domain.util.Result

class NewsRepositoryImpl(
    private val apiServices: NewsAPIServices
) : NewsRepository {

    override suspend fun getNewsByTopic(topic: String, sortBy: String): Result<NewsDTO> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiServices.client.get("/v2/everything") {
                    parameter("q", topic)
                    parameter("sortBy", sortBy)
                    parameter("apiKey", apiServices.apiKey)
                }.body<NewsDTO>()
            }
            Result.Success(response)
        } catch (e: RedirectResponseException) {
            val body = e.response.bodyAsText()
            Result.Error("Redirect (${e.response.status}): $body")
        } catch (e: ClientRequestException) {
            val body = e.response.bodyAsText()
            Result.Error("Client error (${e.response.status}): $body")
        } catch (e: ServerResponseException) {
            val body = e.response.bodyAsText()
            Result.Error("Server error (${e.response.status}): $body")
        } catch (e: Exception) {
            Result.Error("Unexpected error: ${e.message ?: e::class.simpleName}")
        }
    }
}
