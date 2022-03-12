package com.achareh.sample.app

import android.app.Application
import com.achareh.component.di.componentModule
import com.achareh.data.di.dataModule
import com.achareh.sample.di.appModule
import com.achareh.sample.di.viewModelModule
import org.koin.core.context.startKoin

@Suppress("Unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                appModule,
                viewModelModule,
                dataModule,
                componentModule
            )
        }
    }
}