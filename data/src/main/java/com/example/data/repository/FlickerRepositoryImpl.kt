package com.example.data.repository

import com.example.data.mapper.FlickerDataMapper
import com.example.data.source.api.FlickerApi
import com.example.domain.model.FlickerData
import com.example.domain.repository.FlickrRepository

class FlickerRepositoryImpl(
    private val flickrApi: FlickerApi,
    private val mapper: FlickerDataMapper
) : FlickrRepository {
    override suspend fun getItems(tags: String): FlickerData {
        return flickrApi
            .getItems(tags = tags)
            .let {
                mapper.transform(it)
            }
    }
}