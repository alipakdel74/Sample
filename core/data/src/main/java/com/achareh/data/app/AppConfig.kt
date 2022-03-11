package com.achareh.data.app

interface AppConfig {

    fun isDebug(): Boolean

    fun getBaseUrl(): String

}