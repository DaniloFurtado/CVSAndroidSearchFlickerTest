package com.example.domain.usecase

import com.example.domain.model.FlickerDataResult
import com.example.domain.repository.FlickrRepository

class GetFlickrDataUseCase(
    private val flickrRepository: FlickrRepository
) {
    suspend operator fun invoke(
        tags: String
    ): FlickerDataResult? {
        return try {
            flickrRepository
                .getItems(tags)
                .let { FlickerDataResult(data = it) }
        } catch (ex: Throwable) {
            FlickerDataResult(error = ex)
        }
    }
}