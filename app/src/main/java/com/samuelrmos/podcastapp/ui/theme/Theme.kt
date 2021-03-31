package com.samuelrmos.podcastapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PodcastAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = PodcastAppColors,
        typography = PodcastAppTypography,
        shapes = PodcastAppShapes,
        content = content
    )
}