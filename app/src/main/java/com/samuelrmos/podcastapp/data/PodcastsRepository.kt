package com.samuelrmos.podcastapp.data

import android.util.Log
import com.samuelrmos.podcastapp.data.room.CategoryStore
import com.samuelrmos.podcastapp.data.room.TransactionRunner
import com.samuelrmos.podcastapp.di.MainDispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class PodcastsRepository @Inject constructor(
    private val podcastsFetcher: PodcastsFetcher,
    private val podcastStore: PodcastStore,
    private val episodeStore: EpisodeStore,
    private val categoryStore: CategoryStore,
    private val transactionRunner: TransactionRunner,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) {
    private var refreshingJob: Job? = null
    private val scope = CoroutineScope(mainDispatcher)

    @OptIn(InternalCoroutinesApi::class)
    suspend fun updatePodcasts(force: Boolean) {
        if (refreshingJob!!.isActive) {
            refreshingJob?.join()
        } else if (force || podcastStore.isEmpty()) {
            refreshingJob = scope.launch {
                try {
                    podcastsFetcher(SampleFeeds).collect { (podcast, episodes, categories) ->
                        transactionRunner {
                            podcastStore.addPodcast(podcast)
                            episodeStore.addEpisodes(episodes)

                            categories.forEach { category ->
                                val categoryId = categoryStore.addCategory(category)
                                categoryStore.addPodcastToCategory(
                                    podcastUri = podcast.uri,
                                    categoryId = categoryId
                                )
                            }
                        }
                    }
                } catch (error: Throwable) {
                    Log.d(
                        "PodcastsRepository",
                        "podcastsFetcher(SampleFeeds).collect error: $error"
                    )
                }
            }
        }
    }
}