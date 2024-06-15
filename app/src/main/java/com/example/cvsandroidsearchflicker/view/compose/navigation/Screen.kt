package com.example.cvsandroidsearchflicker.view.compose.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object HomeGrid : Screen

    @Serializable
    data object Detail : Screen

}