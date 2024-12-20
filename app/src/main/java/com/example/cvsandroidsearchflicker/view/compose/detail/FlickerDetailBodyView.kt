package com.example.cvsandroidsearchflicker.view.compose.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cvsandroidsearchflicker.extensions.extractTextFromHtml
import com.example.cvsandroidsearchflicker.extensions.toFormattedDateToShow
import com.example.domain.model.FlickerItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FlickerDetailBodyView(
    modifier: Modifier,
    innerPadding: PaddingValues,
    data: FlickerItem?,
    imageKey: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    Column(
        modifier = modifier
            .padding(innerPadding)
            .padding(4.dp)
    ) {
        with(sharedTransitionScope) {
            AsyncImage(
                model = data?.media?.mediaUrl,
                contentDescription = "Image Description",
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = imageKey),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    text = data?.description.extractTextFromHtml(),
                )
                Text(
                    modifier =
                    Modifier
                        .padding(top = 10.dp),
                    text = data?.author.orEmpty(),
                    style = MaterialTheme.typography.bodySmall,
                )

                Text(
                    modifier = Modifier
                        .padding(top = 6.dp),
                    text = data?.published.toFormattedDateToShow(),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}