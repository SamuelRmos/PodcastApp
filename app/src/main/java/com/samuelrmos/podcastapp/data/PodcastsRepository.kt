package com.samuelrmos.podcastapp.data

import com.samuelrmos.podcastapp.data.room.TransactionRunner
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PodcastsRepository @Inject constructor(
    private val podcastsFetcher: PodcastsFetcher,
    private val podcastStore: PodcastStore,
    private val episodeStore: EpisodeStore,
    private val transactionRunner: TransactionRunner,
    mainDispatcher: CoroutineDispatcher
) { }