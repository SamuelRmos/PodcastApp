package com.samuelrmos.podcastapp.util

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun quantityStringResource(@PluralsRes id: Int, quantity: Int) =
    LocalContext.current.resources.getQuantityString(id, quantity)


@Composable
fun quantityStringResource(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any) =
    LocalContext.current.resources.getQuantityString(id, quantity, formatArgs)
