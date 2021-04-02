package com.samuelrmos.podcastapp.data.room

import androidx.room.*
import com.samuelrmos.podcastapp.data.PodcastCategoryEntry

@Dao
abstract class PodcastCategoryEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: PodcastCategoryEntry): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg entity: PodcastCategoryEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: Collection<PodcastCategoryEntry>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: PodcastCategoryEntry)

    @Delete
    abstract suspend fun delete(entity: PodcastCategoryEntry): Int
}