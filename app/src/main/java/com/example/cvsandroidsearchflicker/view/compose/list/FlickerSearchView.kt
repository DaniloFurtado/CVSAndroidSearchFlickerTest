package com.example.cvsandroidsearchflicker.view.compose.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cvsandroidsearchflicker.view.compose.common.LoadingIndicator
import com.example.cvsandroidsearchflicker.view.compose.common.ShowToast
import com.example.domain.model.FlickerData
import com.example.domain.model.FlickerItem
import com.example.domain.model.Media

@Composable
fun FlickerSearchView(
    modifier: Modifier = Modifier,
    uiState: FlickerSearchViewState = FlickerSearchViewState(),
    onSearchTextChanged: (String) -> Unit,
    onNavigationEvent: (FlickerItem) -> Unit = { }
) {
    Scaffold(
        topBar = {
            TopSearchToolbar {
                onSearchTextChanged(it)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            when {
                uiState.isLoading -> LoadingIndicator()
                uiState.error != null -> {
                    ShowToast(message = uiState.error)
                }

                uiState.data != null -> TwoColumnGrid(
                    uiState.data.items,
                    onItemClick = onNavigationEvent
                )
            }
        }
    }
}

@Preview
@Composable
fun ImageSearchAppPreview() {
    FlickerSearchView(
        uiState = FlickerSearchViewState(
            isLoading = false,
            data = FlickerData(
                title = "Mock Flickr Feed",
                link = "https://www.flickr.com/mock",
                description = "A mock Flickr feed for testing",
                items = listOf(
                    FlickerItem(
                        title = "Cute Kitten",
                        link = "https://www.example.com/kitten.jpg",
                        media = Media(mediaUrl = "https://live.staticflickr.com/65535/53758695904_4954b2d59f_m.jpg"),
                        description = "Adorable kitten playing with a ball of yarn.",
                        dateTaken = "2023-05-01T10:00:00Z",
                        published = "2023-05-01T10:00:00Z",
                        author = "Mock Author",
                        authorId = "12345",
                        tags = "kitten, yarn, play"
                    )
                ),
                modified = "2023-05-01T12:00:00Z",
                generator = "Mock Flickr API",
            )
        ),
        onSearchTextChanged = { }
    )
}