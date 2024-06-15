package com.example.cvsandroidsearchflicker.view.compose.detail

import com.example.domain.model.FlickerItem

data class FlickerDetailViewState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: FlickerItem? = null
)