package com.samuelrmos.podcastapp.data

import com.samuelrmos.podcastapp.data.room.EpisodesDao
import kotlinx.coroutines.flow.Flow

class EpisodeStore(private val episodesDao: EpisodesDao) {

    fun episodesInPodcast(
        podcastUri: String,
        limit: Int = Integer.MAX_VALUE
    ): Flow<List<Episode>> = episodesDao.episodesForPodcastUri(podcastUri, limit)

    suspend fun addEpisodes(episodes: Collection<Episode>) = episodesDao.insertAll(episodes)

    suspend fun isEmpty(): Boolean = episodesDao.count() == 0
}