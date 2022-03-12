package com.achareh.sample.di

import com.achareh.sample.viewModel.AddressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AddressViewModel(get()) }

}