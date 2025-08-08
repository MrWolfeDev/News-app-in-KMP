package org.mrwolfedev.insightspark.data.services



import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual fun createHttpClient(): HttpClient = HttpClient(CIO)

