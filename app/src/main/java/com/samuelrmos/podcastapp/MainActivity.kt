package com.samuelrmos.podcastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.samuelrmos.podcastapp.ui.PodcastApp
import com.samuelrmos.podcastapp.ui.theme.PodcastAppTheme

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