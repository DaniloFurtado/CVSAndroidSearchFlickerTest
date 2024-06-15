package com.example.data.source.payload

import com.google.gson.annotations.SerializedName

data class FlickerResponse(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("generator") val generator: String,
    @SerializedName("items") val items: List<FlickerItemResponse>
)

data class FlickerItemResponse(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("media") val mediaResponse: MediaResponse,
    @SerializedName("date_taken") val dateTaken: String,
    @SerializedName("description") val description: String,
    @SerializedName("published") val published: String,
    @SerializedName("author") val author: String,
    @SerializedName("author_id") val authorId: String,
    @SerializedName("tags") val tags: String
)

data class MediaResponse(
    @SerializedName("m") val mediaUrl: String
)