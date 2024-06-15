package com.example.cvsandroidsearchflicker.view.compose.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cvsandroidsearchflicker.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchToolbar(
    onSearchTextChanged: (String) -> Unit
) {
    var text by rememberSaveable {  mutableStateOf("") }

    TopAppBar(
        title = { stringResource(R.string.app_name) },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                    onSearchTextChanged(newText) // Notify about search text changes
                },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp), // Add some padding to the right
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp) // Add padding to the left
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    )
}