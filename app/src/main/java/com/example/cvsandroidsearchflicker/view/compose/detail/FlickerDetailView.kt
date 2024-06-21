package com.example.cvsandroidsearchflicker.view.compose.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FlickerDetailView(
    modifier: Modifier = Modifier,
    uiState: FlickerDetailViewState = FlickerDetailViewState(),
    onBackStackClicked: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
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
            FlickerDetailBodyView(
                modifier,
                innerPadding,
                data,
                uiState.imageKey,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope =  animatedContentScope
            )
        }
    )
}