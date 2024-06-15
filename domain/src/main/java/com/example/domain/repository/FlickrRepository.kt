package com.example.domain.repository

import com.example.domain.model.FlickerData

interface FlickrRepository {
    suspend fun getItems(tags: String): FlickerData
}