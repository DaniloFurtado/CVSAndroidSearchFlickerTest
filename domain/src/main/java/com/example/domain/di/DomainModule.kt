package com.example.domain.di

import com.example.domain.usecase.GetFlickrDataUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetFlickrDataUseCase(get()) }
}