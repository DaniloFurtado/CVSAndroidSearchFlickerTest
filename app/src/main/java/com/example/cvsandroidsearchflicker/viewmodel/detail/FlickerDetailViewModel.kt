package com.example.cvsandroidsearchflicker.viewmodel.detail

import androidx.lifecycle.ViewModel
import com.example.cvsandroidsearchflicker.view.compose.detail.FlickerDetailViewState
import com.example.domain.model.FlickerItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FlickerDetailViewModel(
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _viewState = MutableStateFlow(FlickerDetailViewState())
    val viewState = _viewState.asStateFlow()

    fun setFlickerDetail(item: FlickerItem, imageKey: String){
        _viewState
            .update {
                it.copy(
                    data = item,
                    imageKey = imageKey
                )
            }
    }
}