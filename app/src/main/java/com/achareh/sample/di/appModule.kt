package com.achareh.sample.di

import com.achareh.data.app.AppConfig
import com.achareh.sample.app.AppConfigImpl
import com.achareh.sample.viewModel.AddressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppConfig> { AppConfigImpl() }
    viewModel { AddressViewModel(get()) }
}