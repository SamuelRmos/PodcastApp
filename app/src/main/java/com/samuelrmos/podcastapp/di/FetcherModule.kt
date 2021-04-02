package com.samuelrmos.podcastapp.di

import com.rometools.rome.io.SyndFeedInput
import com.samuelrmos.podcastapp.data.PodcastsFetcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
        ioDispatcher: CoroutineDispatcher
    ): PodcastsFetcher {
        return PodcastsFetcher(okHttpClient, syndFeedInput, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideSyndFeedInput(): SyndFeedInput {
        return SyndFeedInput()
    }

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}