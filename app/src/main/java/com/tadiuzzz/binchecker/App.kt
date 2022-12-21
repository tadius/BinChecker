package com.tadiuzzz.binchecker

import android.app.Application
import com.tadiuzzz.binchecker.di.networkKoinModule
import com.tadiuzzz.binchecker.di.viewModelKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    viewModelKoinModule,
                    networkKoinModule
                )
            )
        }
    }
}