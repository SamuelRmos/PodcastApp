package com.samuelrmos.podcastapp.data.room

import androidx.room.*
import com.samuelrmos.podcastapp.data.PodcastFollowedEntry

@Dao
abstract class PodcastFollowedEntryDao {
    @Query("DELETE FROM podcast_followed_entries WHERE podcast_uri = :podcastUri")
    abstract suspend fun deleteWithPodcastUri(podcastUri: String)

    @Query("SELECT COUNT(*) FROM podcast_followed_entries WHERE podcast_uri = :podcastUri")
    protected abstract suspend fun podcastFollowRowCount(podcastUri: String): Int

    suspend fun isPodcastFollowed(podcastUri: String): Boolean =
        podcastFollowRowCount(podcastUri) > 0

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: PodcastFollowedEntry): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg entity: PodcastFollowedEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: Collection<PodcastFollowedEntry>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: PodcastFollowedEntry)

    @Delete
    abstract suspend fun delete(entity: PodcastFollowedEntry): Int
}