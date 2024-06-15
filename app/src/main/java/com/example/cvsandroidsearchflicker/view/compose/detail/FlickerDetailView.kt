package com.example.cvsandroidsearchflicker.view.compose.detail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FlickerDetailView(
    modifier: Modifier = Modifier,
    uiState: FlickerDetailViewState = FlickerDetailViewState(),
    onBackStackClicked: () -> Unit
) {
    val data = uiState.data
    Scaffold(
        topBar = {
            TopBarDetail(
                onBackStackClicked,
                title = data?.title.orEmpty(),
            )
        },
        bottomBar = {},
        content = { innerPadding ->
            FlickerDetailBodyView(modifier, innerPadding, data)
        }
    )
}