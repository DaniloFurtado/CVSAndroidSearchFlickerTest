package com.example.domain.usecase

import com.example.domain.model.FlickerData
import com.example.domain.model.FlickerItem
import com.example.domain.model.Media
import com.example.domain.repository.FlickrRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class GetFlickerDataDetailUseCaseTest {

    private val flickrRepository = mockk<FlickrRepository>()
    private lateinit var getFlickrDataUseCase: GetFlickrDataUseCase
    private val mockFlickerData = FlickerData(
        title = "Mock Flickr Feed",
        link = "https://www.flickr.com/mock",
        description = "A mock Flickr feed for testing",
        items = listOf(
            FlickerItem(
                title = "Cute Kitten",
                link = "https://www.example.com/kitten.jpg",
                media = Media(mediaUrl = "https://www.example.com/kitten_thumbnail.jpg"),
                description = "Adorable kitten playing with a ball of yarn.",
                dateTaken = "2023-05-01T10:00:00Z",
                published = "2023-05-01T10:00:00Z",
                author = "Mock Author",
                authorId = "12345",
                tags = "kitten, yarn, play"
            )
        ),
        modified = "2023-05-01T12:00:00Z",
        generator = "Mock Flickr API",
    )

    @Before
    fun setup() {
        getFlickrDataUseCase = GetFlickrDataUseCase(flickrRepository)
    }

    @Test
    fun `execute request and get data with success`() = runBlocking {
        coEvery {
            flickrRepository.getItems(any())
        } returns mockFlickerData

        val result = getFlickrDataUseCase("kitten")

        assertEquals(mockFlickerData, result?.data)

    }

    @Test
    fun `execute request and get an io exception error`() = runBlocking {
        val error = IOException("Request Error")
        coEvery {
            flickrRepository.getItems(any())
        }.throws(error)

        val result = getFlickrDataUseCase("kitten")
        assertEquals(
            result?.error,
            error
        )

    }

    @Test
    fun `execute request and get an exception error`() = runBlocking {
        val error = Exception("Request Error")
        coEvery {
            flickrRepository.getItems(any())
        }.throws(error)

        val result = getFlickrDataUseCase("kitten")

        assertEquals(
            result?.error,
            error
        )
    }
}