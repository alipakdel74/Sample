package com.achareh.component.di

import com.achareh.component.vlidator.Validator
import com.achareh.component.vlidator.ValidatorImpl
import org.koin.dsl.module

val componentModule = module {

    single<Validator> { ValidatorImpl() }

}