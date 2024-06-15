package com.example.cvsandroidsearchflicker.viewmodel.list

import com.example.domain.model.FlickerData
import com.example.domain.model.FlickerDataResult
import com.example.domain.model.FlickerItem
import com.example.domain.model.Media

internal val flickerData = FlickerDataResult(
    data = FlickerData(
        title = "Sample Flicker Data",
        link = "https://www.example.com/flicker",
        description = "A sample description for Flicker data.",
        modified = "2023-12-07T12:00:00Z",
        generator = "Flicker API",
        items = listOf(
            FlickerItem(
                title = "Item 1",
                link = "https://www.example.com/item1",
                media = Media(mediaUrl = "https://www.example.com/image1.jpg"),
                dateTaken = "2023-12-01",
                description = "Description for item 1.",
                published = "2023-12-02T09:00:00Z",
                author = "Author 1",
                authorId = "12345",
                tags = "tag1, tag2"
            ),
            FlickerItem(
                title = "Item 2",
                link = "https://www.example.com/item2",
                media = Media(mediaUrl = "https://www.example.com/image2.jpg"),
                dateTaken = "2023-12-03",
                description = "Description for item 2.",
                published = "2023-12-04T15:30:00Z",
                author = "Author 2",
                authorId = "67890",
                tags = "tag3, tag4"
            )
            // Add more FlickerItem objects as needed
        )
    )
)