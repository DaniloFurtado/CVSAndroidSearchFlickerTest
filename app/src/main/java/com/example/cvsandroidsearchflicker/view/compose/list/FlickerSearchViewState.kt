package com.example.cvsandroidsearchflicker.view.compose.list

import com.example.domain.model.FlickerData

data class FlickerSearchViewState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: FlickerData? = null
)