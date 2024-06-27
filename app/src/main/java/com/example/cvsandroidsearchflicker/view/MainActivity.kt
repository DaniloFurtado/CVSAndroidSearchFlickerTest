package com.example.cvsandroidsearchflicker.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cvsandroidsearchflicker.di.appModule
import com.example.cvsandroidsearchflicker.ui.theme.CVSAndroidSearchFlickerTheme
import com.example.cvsandroidsearchflicker.view.compose.navigation.AppNavHost
import com.example.cvsandroidsearchflicker.viewmodel.detail.FlickerDetailViewModel
import com.example.cvsandroidsearchflicker.viewmodel.list.FlickerSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivity : ComponentActivity() {
    private val _flickrViewModel: FlickerSearchViewModel by viewModel()
    private val _flickerViewModel: FlickerDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(appModule)
        enableEdgeToEdge()
        setContent {
            CVSAndroidSearchFlickerTheme {
                AppNavHost(
                    modifier = Modifier.fillMaxSize(),
                    flickerViewModel = _flickrViewModel,
                    flickerDetailViewModel = _flickerViewModel
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(appModule)
    }
}
