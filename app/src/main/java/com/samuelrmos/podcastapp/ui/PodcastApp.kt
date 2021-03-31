package com.samuelrmos.podcastapp.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.samuelrmos.podcastapp.R
import com.samuelrmos.podcastapp.ui.home.Home

@Composable
fun PodcastApp() {
    val context = LocalContext.current
    var isOnline by remember { mutableStateOf(checkIfOnline(context)) }

    if (isOnline) {
        Home()
    } else {
        OfflineDialog { isOnline = checkIfOnline(context) }
    }
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.connection_error_title)) },
        text = { Text(text = stringResource(R.string.connection_error_message))},
        confirmButton = {
            TextButton(onClick =  onRetry ) {
                Text(text = stringResource(R.string.retry_label))
            }
        }
    )
}

@Suppress("DEPRECATION")
private fun checkIfOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}
