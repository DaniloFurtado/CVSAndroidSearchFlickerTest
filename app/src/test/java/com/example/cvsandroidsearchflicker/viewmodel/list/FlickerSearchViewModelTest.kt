package com.example.cvsandroidsearchflicker.viewmodel.list

import com.example.domain.model.FlickerDataResult
import com.example.domain.usecase.GetFlickrDataUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FlickerSearchViewModelTest {

    private val coroutineDispatcher = UnconfinedTestDispatcher()
    private val getFlickrDataUseCase = mockk<GetFlickrDataUseCase>(relaxed = true)
    private lateinit var viewModel: FlickerSearchViewModel

    @Before
    fun setup() {
        viewModel = FlickerSearchViewModel(
            getFlickrDataUseCase = getFlickrDataUseCase,
            dispatcher = coroutineDispatcher
        )
    }

    @Test
    fun `should show data on the view when request is success`() = runBlocking {

        coEvery {
            getFlickrDataUseCase(any())
        } returns flickerData

        viewModel.getFlickrData("porcupine")

        assertNull(
            viewModel.viewState.value.error
        )

        assertEquals(
            viewModel.viewState.value.data?.title, flickerData.data?.title
        )

        assertEquals(
            viewModel.viewState.value.data?.description, flickerData.data?.description
        )
    }

    @Test
    fun `should show error when request is failed`() = runBlocking {

        coEvery {
            getFlickrDataUseCase(any())
        } returns FlickerDataResult(error = Throwable("Error Request"))

        viewModel.getFlickrData("porcupine")

        assertNull(
            viewModel.viewState.value.data
        )

        assertNotNull(
            viewModel.viewState.value.error
        )

        assertEquals(
            viewModel.viewState.value.error,
            "Error Request"
        )
    }

    @Test
    fun `should show empty view when tags is empty`() = runBlocking {
        viewModel.getFlickrData("")

        assertEquals(
            viewModel.viewState.value.isLoading, false
        )

        assertNull(
            viewModel.viewState.value.error
        )

        assertNull(
            viewModel.viewState.value.data
        )
    }

}