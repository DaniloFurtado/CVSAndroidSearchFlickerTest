package com.example.cvsandroidsearchflicker.view.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cvsandroidsearchflicker.view.compose.detail.FlickerDetailView
import com.example.cvsandroidsearchflicker.view.compose.detail.FlickerDetailViewState
import com.example.cvsandroidsearchflicker.view.compose.list.FlickerSearchView
import com.example.cvsandroidsearchflicker.viewmodel.detail.FlickerDetailViewModel
import com.example.cvsandroidsearchflicker.viewmodel.list.FlickerSearchViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    flickerViewModel: FlickerSearchViewModel,
    flickerDetailViewModel: FlickerDetailViewModel
) {
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
                onNavigationEvent = { flickerItem ->
                    flickerDetailViewModel.setFlickerDetail(flickerItem)
                    navController.navigate(Screen.Detail)
                }
            )
        }

        composable<Screen.Detail> {
            FlickerDetailView(
                uiState = uiDetailState,
                onBackStackClicked = {
                    navController
                        .previousBackStackEntry
                        ?.let { navController.popBackStack() }
                }
            )
        }
    }
}
