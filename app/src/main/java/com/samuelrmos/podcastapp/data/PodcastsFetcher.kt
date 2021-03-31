package com.samuelrmos.podcastapp.data

import com.rometools.rome.io.SyndFeedInput
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class PodcastsFetcher(
    private val okHttpClient: OkHttpClient,
    private val syndFeedInput: SyndFeedInput,
    private val ioDispatcher: CoroutineDispatcher
) {
}