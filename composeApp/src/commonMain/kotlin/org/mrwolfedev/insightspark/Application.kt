// commonMain
package org.mrwolfedev.insightspark

import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger
import org.mrwolfedev.insightspark.di.sharedModules

fun initKoin() {
    startKoin {
        logger(PrintLogger(Level.INFO))
        modules(sharedModules)
    }
}
