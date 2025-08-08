package com.wolf.news.domain.repository.Location

interface LocationProvider {
    suspend fun getCountryCode(): String?
}