package com.samuelrmos.podcastapp.di

import com.rometools.rome.io.SyndFeedInput
import com.samuelrmos.podcastapp.data.PodcastsFetcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FetcherModule {

    @Provides
    @Singleton
    fun providePodcastsFetcher(
        okHttpClient: OkHttpClient,
        syndFeedInput: SyndFeedInput,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): PodcastsFetcher {
        return PodcastsFetcher(okHttpClient, syndFeedInput, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideSyndFeedInput(): SyndFeedInput {
        return SyndFeedInput()
    }
}