package com.samuelrmos.podcastapp.data

import com.rometools.rome.io.SyndFeedInput
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import javax.inject.Inject

class PodcastsFetcher @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val syndFeedInput: SyndFeedInput,
    private val ioDispatcher: CoroutineDispatcher
)