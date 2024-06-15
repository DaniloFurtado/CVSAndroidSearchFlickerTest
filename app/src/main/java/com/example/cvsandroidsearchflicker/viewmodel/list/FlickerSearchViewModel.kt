package com.example.cvsandroidsearchflicker.viewmodel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvsandroidsearchflicker.view.compose.list.FlickerSearchViewState
import com.example.domain.usecase.GetFlickrDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlickerSearchViewModel(
    private val getFlickrDataUseCase: GetFlickrDataUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow(FlickerSearchViewState())
    val viewState = _viewState.asStateFlow()

    fun getFlickrData(tags: String) {
        if (tags.isNotEmpty()) {
            viewModelScope.launch(dispatcher) {
                _viewState.update {
                    it.copy(isLoading = true)
                }

                getFlickrDataUseCase(tags).let { result ->
                    if (result?.error != null) {
                        _viewState.update {
                            it.copy(isLoading = false, error = result.error?.message)
                        }
                    } else {
                        _viewState.update {
                            it.copy(isLoading = false, data = result?.data)
                        }
                    }
                }
            }
        } else {
            _viewState.value = FlickerSearchViewState()
        }
    }

}