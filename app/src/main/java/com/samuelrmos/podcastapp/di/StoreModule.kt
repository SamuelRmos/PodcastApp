package com.samuelrmos.podcastapp.di

import com.samuelrmos.podcastapp.data.EpisodeStore
import com.samuelrmos.podcastapp.data.PodcastStore
import com.samuelrmos.podcastapp.data.room.*
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

    @Provides
    @Singleton
    fun provideCategoryStore(
        categoriesDao: CategoriesDao,
        categoryEntryDao: PodcastCategoryEntryDao,
        episodesDao: EpisodesDao,
        podcastDao: PodcastDao
    ): CategoryStore {
        return CategoryStore(categoriesDao, categoryEntryDao, episodesDao, podcastDao)
    }
}