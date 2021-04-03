package com.samuelrmos.podcastapp.data.room

import com.samuelrmos.podcastapp.data.Category
import com.samuelrmos.podcastapp.data.EpisodeToPodcast
import com.samuelrmos.podcastapp.data.PodcastCategoryEntry
import com.samuelrmos.podcastapp.data.PodcastWithExtraInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryStore @Inject constructor(
    private val categoriesDao: CategoriesDao,
    private val categoryEntryDao: PodcastCategoryEntryDao,
    private val episodesDao: EpisodesDao,
    private val podcastDao: PodcastDao
) {
    fun categoriesSortedByPodcastCount(
        limit: Int = Integer.MAX_VALUE
    ): Flow<List<Category>> {
        return categoriesDao.categoriesSortedByPodcastCount(limit)
    }

    fun podcastsInCategorySortedByPodcastCount(
        categoryId: Long,
        limit: Int = Int.MAX_VALUE
    ): Flow<List<PodcastWithExtraInfo>> {
        return podcastDao.podcastsInCategorySortedByLastEpisode(categoryId, limit)
    }

    fun episodesFromPodcastsInCategory(
        categoryId: Long,
        limit: Int = Integer.MAX_VALUE
    ): Flow<List<EpisodeToPodcast>> {
        return episodesDao.episodesFromPodcastsInCategory(categoryId, limit)
    }

    suspend fun addCategory(category: Category): Long {
        return when (val local = categoriesDao.getCategoryWithName(category.name)) {
            null -> categoriesDao.insert(category)
            else -> local.id
        }
    }

    suspend fun addPodcastToCategory(podcastUri: String, categoryId: Long) {
        categoryEntryDao.insert(
            PodcastCategoryEntry(podcastUri = podcastUri, categoryId = categoryId)
        )
    }
}