package com.achareh.data.network

import com.achareh.data.app.AppConfig
import com.achareh.data.network.interceptor.HeaderInterceptor
import com.achareh.data.network.interceptor.LoggingInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiProvider(
    private val moshi: Moshi,
    private val appConfig: AppConfig,
    private val headerProvider: (() -> Map<String, String?>?)? = null
) {

    fun retrofit(): Retrofit {

        val client = OkHttpClient.Builder().apply {

            connectTimeout(API_REQ_CONNECTION_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            readTimeout(API_REQ_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            writeTimeout(API_REQ_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            if (headerProvider != null) {
                addInterceptor(HeaderInterceptor(headerProvider))
            }
            if (appConfig.isDebug()) {
                addInterceptor(LoggingInterceptor())
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }

        }.build()

        return Retrofit.Builder()
            .baseUrl(appConfig.getBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(client)
            .build()
    }

    companion object {
        const val API_REQ_CONNECTION_TIMEOUT_MILLIS = 5000L
        const val API_REQ_READ_TIMEOUT_MILLIS = 5000L
        const val API_REQ_WRITE_TIMEOUT_MILLIS = 5000L
    }

}
