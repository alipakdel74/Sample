package com.achareh.sample.di

import com.achareh.data.app.AppConfig
import com.achareh.sample.app.AppConfigImpl
import org.koin.dsl.module

val appModule = module {
    single<AppConfig> { AppConfigImpl() }
}