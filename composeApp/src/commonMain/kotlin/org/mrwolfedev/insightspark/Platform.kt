package org.mrwolfedev.insightspark

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform