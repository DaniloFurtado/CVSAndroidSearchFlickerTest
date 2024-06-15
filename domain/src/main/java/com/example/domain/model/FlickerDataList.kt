package com.example.domain.model

data class FlickerData(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<FlickerItem>,
    var error: Exception? = null
)

data class FlickerItem(
    val title: String,
    val link: String,
    val media: Media,
    val dateTaken: String,
    val description: String,
    val published: String,
    val author: String,
    val authorId: String,
    val tags: String
)

data class Media(
    val mediaUrl: String
)