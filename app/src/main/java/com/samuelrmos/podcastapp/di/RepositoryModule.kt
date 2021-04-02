package com.samuelrmos.podcastapp.di

import com.rometools.rome.io.SyndFeedInput
import com.samuelrmos.podcastapp.data.EpisodeStore
import com.samuelrmos.podcastapp.data.PodcastStore
import com.samuelrmos.podcastapp.data.PodcastsFetcher
import com.samuelrmos.podcastapp.data.PodcastsRepository
import com.samuelrmos.podcastapp.data.room.TransactionRunner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providePodcastsRepository(
        podcastsFetcher: PodcastsFetcher,
        podcastStore: PodcastStore,
        episodeStore: EpisodeStore,
        transactionRunner: TransactionRunner,
        mainDispatcher: CoroutineDispatcher
    ): PodcastsRepository {
        return PodcastsRepository(
            podcastsFetcher,
            podcastStore,
            episodeStore,
            transactionRunner,
            mainDispatcher
        )
    }

    @Provides
    @Singleton
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}