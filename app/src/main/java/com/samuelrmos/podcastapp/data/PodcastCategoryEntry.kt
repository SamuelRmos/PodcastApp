package com.samuelrmos.podcastapp.data

import androidx.compose.runtime.Immutable
import androidx.room.*

@Entity(
    tableName = "podcast_category_entries",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Podcast::class,
            parentColumns = ["uri"],
            childColumns = ["podcast_uri"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("podcast_uri", "category_id", unique = true),
        Index("category_id"),
        Index("podcast_uri")
    ]
)

@Immutable
data class PodcastCategoryEntry(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "podcast_uri") val podcastUri: String,
    @ColumnInfo(name = "category_id") val categoryId: Long
)