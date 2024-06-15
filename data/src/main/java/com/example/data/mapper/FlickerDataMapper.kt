package com.example.data.mapper

import com.example.data.source.payload.FlickerItemResponse
import com.example.data.source.payload.FlickerResponse
import com.example.domain.model.FlickerData
import com.example.domain.model.FlickerItem
import com.example.domain.model.Media

class FlickerDataMapper {

    suspend fun transform(flickrResponse: FlickerResponse): FlickerData {
        return FlickerData(
            title = flickrResponse.title,
            link = flickrResponse.link,
            description = flickrResponse.description,
            modified = flickrResponse.modified,
            generator = flickrResponse.generator,
            items = flickrResponse.items.map { item ->
                transform(item)
            }
        )
    }

    private fun transform(flickrItemResponse: FlickerItemResponse): FlickerItem {
        return FlickerItem(
            title = flickrItemResponse.title,
            link = flickrItemResponse.link,
            media = Media(flickrItemResponse.mediaResponse.mediaUrl),
            dateTaken = flickrItemResponse.dateTaken,
            description = flickrItemResponse.description,
            published = flickrItemResponse.published,
            author = flickrItemResponse.author,
            authorId = flickrItemResponse.authorId,
            tags = flickrItemResponse.tags
        )
    }
}