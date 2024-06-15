package com.example.cvsandroidsearchflicker.di

import com.example.cvsandroidsearchflicker.viewmodel.detail.FlickerDetailViewModel
import com.example.cvsandroidsearchflicker.viewmodel.list.FlickerSearchViewModel
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { FlickerSearchViewModel(get(), get()) }
    viewModel { FlickerDetailViewModel(get()) }
    factory { Dispatchers.IO }
}

val appModule = listOf(
    dataModule,
    domainModule,
    presentationModule
)