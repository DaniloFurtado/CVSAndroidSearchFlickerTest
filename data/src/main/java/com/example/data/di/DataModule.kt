package com.example.data.di

import com.example.data.mapper.FlickerDataMapper
import com.example.data.repository.FlickerRepositoryImpl
import com.example.data.source.remote.api.FlickerApi
import com.example.domain.repository.FlickrRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<FlickrRepository> {
        FlickerRepositoryImpl(get(), get())
    }

    factory {
        get<Retrofit>()
            .create(FlickerApi::class.java)
    }

    factory<FlickerDataMapper> {
        FlickerDataMapper()
    }

    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}