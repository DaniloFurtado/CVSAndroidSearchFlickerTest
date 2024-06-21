package com.example.cvsandroidsearchflicker.view.compose.list

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.FlickerItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TwoColumnGrid(
    modifier: Modifier = Modifier,
    items: List<FlickerItem>,
    onItemClick: (FlickerItem, imageKey: String) -> Unit = { _, _ -> },
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        LazyVerticalGrid(
            modifier = modifier.padding(8.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size) { index ->
                val imageKey = "$index-${items[index].media.mediaUrl}"
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clickable { onItemClick(items[index], imageKey) },
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(items[index].media.mediaUrl)
                            .crossfade(true)
                            .placeholderMemoryCacheKey(imageKey)
                            .memoryCacheKey(imageKey)
                            .build(),
                        contentDescription = "Image Description",
                        modifier = Modifier
                            .fillMaxSize()
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = imageKey),
                                animatedVisibilityScope = animatedContentScope
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}