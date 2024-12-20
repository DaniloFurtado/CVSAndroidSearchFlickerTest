package com.example.data.source.remote.api

import com.example.data.source.remote.payload.FlickerResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val GET_ITEMS = "services/feeds/photos_public.gne"

interface FlickerApi {
    @GET(GET_ITEMS)
    suspend fun getItems(
        @Query("tags") tags: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): FlickerResponse
}
