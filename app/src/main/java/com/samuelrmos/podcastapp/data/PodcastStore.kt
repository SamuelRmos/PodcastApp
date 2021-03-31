package com.samuelrmos.podcastapp.data

import com.samuelrmos.podcastapp.data.room.PodcastDao
import com.samuelrmos.podcastapp.data.room.PodcastFollowedEntryDao
import com.samuelrmos.podcastapp.data.room.TransactionRunner
import kotlinx.coroutines.flow.Flow

class PodcastStore(
    private val podcastDao: PodcastDao,
    private val podcastFollowedEntryDao: PodcastFollowedEntryDao,
    private val transactionRunner: TransactionRunner
) {

    fun podcastWithUri(uri: String): Flow<Podcast> = podcastDao.podcastWithUri(uri)

    fun podcastsSortedByLastEpisode(
        limit: Int = Int.MAX_VALUE
    ): Flow<List<PodcastWithExtraInfo>> = podcastDao.followedPodcastsSortedByLastEpisode(limit)

    fun followedPodcastsSortedByLastEpisode(
        limit: Int = Int.MAX_VALUE
    ): Flow<List<PodcastWithExtraInfo>> {
        return podcastDao.followedPodcastsSortedByLastEpisode(limit)
    }

    private suspend fun followPodcast(podcastUri: String) {
        podcastFollowedEntryDao.insert(PodcastFollowedEntry(podcastUri = podcastUri))
    }

    suspend fun togglePodcastFollowed(podcastUri: String) = transactionRunner {
        if (podcastFollowedEntryDao.isPodcastFollowed(podcastUri)) {
            unfollowPodcast(podcastUri)
        } else {
            followPodcast(podcastUri)
        }
    }

    suspend fun unfollowPodcast(podcastUri: String) {
        podcastFollowedEntryDao.deleteWithPodcastUri(podcastUri)
    }

    suspend fun addPodcast(podcast: Podcast) {
        podcastDao.insert(podcast)
    }

    suspend fun isEmpty(): Boolean = podcastDao.count() == 0
}