package com.achareh.sample.app

import com.achareh.data.app.AppConfig
import com.achareh.sample.BuildConfig

class AppConfigImpl: AppConfig {
    override fun isDebug() = BuildConfig.DEBUG

    override fun getBaseUrl() = BuildConfig.BUILD_TYPE
}