package com.samuelrmos.podcastapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.samuelrmos.podcastapp.ui.PodcastApp
import com.samuelrmos.podcastapp.ui.theme.PodcastAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PodcastAppTheme {
                ProvideWindowInsets {
                    PodcastApp()
                }
            }
        }
    }
}