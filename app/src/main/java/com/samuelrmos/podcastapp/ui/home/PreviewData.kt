package com.samuelrmos.podcastapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import com.samuelrmos.podcastapp.data.Podcast
import com.samuelrmos.podcastapp.data.PodcastWithExtraInfo
import java.time.OffsetDateTime

val PreviewPodcasts = listOf(
    Podcast(
        uri = "fakeUri://podcast/1",
        title = "Android Developers Backstage",
        author = "Android Developers"
    ),
    Podcast(
        uri = "fakeUri://podcast/2",
        title = "Google Developers podcast",
        author = "Google Developers"
    )

)

@RequiresApi(Build.VERSION_CODES.O)
val PreviewPodcastsWithExtraInfo = PreviewPodcasts.mapIndexed { index, podcast ->
    PodcastWithExtraInfo().apply {
        this.podcast = podcast
        this.lastEpisodeDate = OffsetDateTime.now()
        this.isFollowed = index % 2 == 0
    }
}