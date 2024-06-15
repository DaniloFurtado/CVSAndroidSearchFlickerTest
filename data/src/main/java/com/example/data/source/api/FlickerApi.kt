package com.example.data.source.api

import com.example.data.source.payload.FlickerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerApi {
    @GET(GET_ITEMS)
    suspend fun getItems(
        @Query("tags") tags: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): FlickerResponse


    companion object {
        private const val GET_ITEMS = "services/feeds/photos_public.gne"
    }
}
