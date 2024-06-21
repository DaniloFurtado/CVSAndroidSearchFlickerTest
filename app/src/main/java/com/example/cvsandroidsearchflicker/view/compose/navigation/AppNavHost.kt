package com.example.cvsandroidsearchflicker.view.compose.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cvsandroidsearchflicker.view.compose.detail.FlickerDetailView
import com.example.cvsandroidsearchflicker.view.compose.list.FlickerSearchView
import com.example.cvsandroidsearchflicker.viewmodel.detail.FlickerDetailViewModel
import com.example.cvsandroidsearchflicker.viewmodel.list.FlickerSearchViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    flickerViewModel: FlickerSearchViewModel,
    flickerDetailViewModel: FlickerDetailViewModel
) {
    SharedTransitionLayout {
        val navController = rememberNavController()
        val uiListState by flickerViewModel.viewState.collectAsState()
        val uiDetailState by flickerDetailViewModel.viewState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Screen.HomeGrid,
            modifier = modifier
        ) {
            composable<Screen.HomeGrid> {
                FlickerSearchView(
                    uiState = uiListState,
                    onSearchTextChanged = flickerViewModel::getFlickrData,
                    onNavigationEvent = { flickerItem, index ->
                        flickerDetailViewModel.setFlickerDetail(flickerItem, index)
                        navController.navigate(Screen.Detail)
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }

            composable<Screen.Detail> { _ ->
                FlickerDetailView(
                    uiState = uiDetailState,
                    onBackStackClicked = {
                        navController
                            .previousBackStackEntry
                            ?.let { navController.popBackStack() }
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
        }
    }
}
