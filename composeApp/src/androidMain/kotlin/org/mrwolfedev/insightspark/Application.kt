package org.mrwolfedev.insightspark


import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.mrwolfedev.insightspark.di.sharedModules

class InsightSparkApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@InsightSparkApplication)
            modules(sharedModules)
        }
    }
}