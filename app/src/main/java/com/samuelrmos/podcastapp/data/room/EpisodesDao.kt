package com.samuelrmos.podcastapp.data.room

import androidx.room.*
import com.samuelrmos.podcastapp.data.Episode
import com.samuelrmos.podcastapp.data.EpisodeToPodcast
import kotlinx.coroutines.flow.Flow

@Dao
abstract class EpisodesDao {

    @Query(
        """
        SELECT * FROM episodes WHERE podcast_uri = :podcastUri
        ORDER BY datetime(published) DESC
        LIMIT :limit
    """
    )
    abstract fun episodesForPodcastUri(
        podcastUri: String,
        limit: Int
    ): Flow<List<Episode>>

    @Transaction
    @Query(
        """
        SELECT episodes.* FROM episodes
        INNER JOIN podcast_category_entries ON episodes.podcast_uri = podcast_category_entries.podcast_uri
        WHERE category_id = :categoryId
        ORDER BY datetime(published) DESC
        LIMIT :limit
        """
    )
    abstract fun episodesFromPodcastsInCategory(
        categoryId: Long,
        limit: Int
    ): Flow<List<EpisodeToPodcast>>

    @Query("SELECT COUNT(*) FROM episodes")
    abstract suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Episode): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg entity: Episode)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: Collection<Episode>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: Episode)

    @Delete
    abstract suspend fun delete(entity: Episode): Int
}