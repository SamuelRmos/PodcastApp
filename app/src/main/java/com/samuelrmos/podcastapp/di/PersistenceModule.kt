package com.samuelrmos.podcastapp.di

import android.content.Context
import androidx.room.Room
import com.samuelrmos.podcastapp.data.room.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun providePodcastAppDatabase(@ApplicationContext context: Context): PodcastAppDatabase {
        return Room.databaseBuilder(context, PodcastAppDatabase::class.java, "data.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePodcastDao(podcastAppDatabase: PodcastAppDatabase): PodcastDao {
        return podcastAppDatabase.podcastDao()
    }

    @Provides
    @Singleton
    fun provideEpisodesDao(podcastAppDatabase: PodcastAppDatabase): EpisodesDao {
        return podcastAppDatabase.episodesDao()
    }

    @Provides
    @Singleton
    fun provideCategoriesDao(podcastAppDatabase: PodcastAppDatabase): CategoriesDao {
        return podcastAppDatabase.categoriesDao()
    }

    @Provides
    @Singleton
    fun providePodcastCategoryEntryDao(podcastAppDatabase: PodcastAppDatabase): PodcastCategoryEntryDao {
        return podcastAppDatabase.podcastCategoryEntryDao()
    }

    @Provides
    @Singleton
    fun provideTransactionRunnerDao(podcastAppDatabase: PodcastAppDatabase): TransactionRunnerDao {
        return podcastAppDatabase.transactionRunnerDao()
    }

    @Provides
    @Singleton
    fun providePodcastFollowedEntryDao(podcastAppDatabase: PodcastAppDatabase): PodcastFollowedEntryDao {
        return podcastAppDatabase.podcastFollowedEntryDao()
    }
}