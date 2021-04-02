package com.samuelrmos.podcastapp.di

import com.samuelrmos.podcastapp.data.EpisodeStore
import com.samuelrmos.podcastapp.data.PodcastStore
import com.samuelrmos.podcastapp.data.room.EpisodesDao
import com.samuelrmos.podcastapp.data.room.PodcastDao
import com.samuelrmos.podcastapp.data.room.PodcastFollowedEntryDao
import com.samuelrmos.podcastapp.data.room.TransactionRunner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    @Singleton
    fun providePodcastStore(
        podcastDao: PodcastDao,
        podcastFollowedEntryDao: PodcastFollowedEntryDao,
        transactionRunner: TransactionRunner
    ): PodcastStore {
        return PodcastStore(podcastDao, podcastFollowedEntryDao, transactionRunner)
    }

    @Provides
    @Singleton
    fun provideEpisodeStore(episodesDao: EpisodesDao): EpisodeStore {
        return EpisodeStore(episodesDao)
    }
}