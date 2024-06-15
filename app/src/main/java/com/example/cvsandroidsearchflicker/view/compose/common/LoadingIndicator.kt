package com.example.cvsandroidsearchflicker.view.compose.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleProgressIndicator(
            modifier = Modifier
                .size(64.dp),
            isCircular = true,
        )
    }
}

@Composable
fun SimpleProgressIndicator(
    modifier: Modifier = Modifier,
    isCircular: Boolean = false
) {
    if (isCircular) {
        CircularProgressIndicator(
            modifier = modifier
        )
    } else {
        LinearProgressIndicator(modifier = modifier)
    }
}