package com.achareh.data.di

import com.achareh.data.network.ApiProvider
import com.achareh.data.network.service.AcharehApi
import com.achareh.data.repository.AcharehRepo
import com.achareh.data.repository.AcharehRepoImpl
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<Moshi> {
        Moshi.Builder().build()
    }

    single {
        ApiProvider(get(), get()) {
            hashMapOf("Authorization" to "Basic MDk4MjIyMjIyMjI6c2FuYTEyMzQ=")
        }.retrofit()
    }

    // api
    single { get<Retrofit>().create(AcharehApi::class.java) }

    // repository
    single<AcharehRepo> { AcharehRepoImpl(get()) }

}