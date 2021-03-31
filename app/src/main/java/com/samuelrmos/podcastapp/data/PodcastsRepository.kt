package com.samuelrmos.podcastapp.data

import com.samuelrmos.podcastapp.data.room.TransactionRunner
import kotlinx.coroutines.CoroutineDispatcher

class PodcastsRepository(
    private val podcastsFetcher: PodcastsFetcher,
    private val podcastStore: PodcastStore,
    private val episodeStore: EpisodeStore,
    private val transactionRunner: TransactionRunner,
    mainDispatcher: CoroutineDispatcher
) { }