package com.samuelrmos.podcastapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val Yellow800 = Color(0xFFF29F05)
val Red300 = Color(0xFFEA6D7E)

fun Colors.compositedOnSurface(alpha: Float): Color =
    onSurface.copy(alpha = alpha).compositeOver(surface)

val PodcastAppColors = darkColors(
    primary = Yellow800,
    onPrimary = Color.Black,
    primaryVariant = Yellow800,
    secondary = Yellow800,
    onSecondary = Color.Black,
    error = Red300,
    onError = Color.Black
)