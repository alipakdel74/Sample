package com.achareh.sample.app

import android.app.Application
import com.achareh.data.di.dataModule
import com.achareh.sample.di.appModule
import org.koin.core.context.startKoin

@Suppress("Unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                appModule,
                dataModule
            )
        }
    }
}