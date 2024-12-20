package com.example.cvsandroidsearchflicker.viewmodel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvsandroidsearchflicker.view.compose.list.FlickerSearchViewState
import com.example.domain.usecase.GetFlickrDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlickerSearchViewModel(
    private val getFlickrDataUseCase: GetFlickrDataUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow(FlickerSearchViewState())
    val viewState = _viewState.asStateFlow()

    fun getFlickrData(tags: String) {
        if (tags.isNotEmpty()) {
            viewModelScope.launch(dispatcher) {
                showLoading()

                getFlickrDataUseCase(tags).let { result ->
                    withContext(Dispatchers.Main) {
                        if (result?.data != null) {
                            _viewState.update {
                                it.copy(data = result.data)
                            }
                        } else {
                            _viewState.update {
                                it.copy(error = result?.error?.message)
                            }
                        }
                        hideLoading()
                    }
                }
            }
        } else {
            _viewState.value = FlickerSearchViewState()
        }
    }

    private fun showLoading() {
        _viewState.update {
            it.copy(isLoading = true)
        }
    }

    private fun hideLoading() {
        _viewState.update {
            it.copy(isLoading = false)
        }
    }

}