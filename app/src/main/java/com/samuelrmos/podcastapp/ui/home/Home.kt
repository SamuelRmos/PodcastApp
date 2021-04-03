package com.samuelrmos.podcastapp.ui.home

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.samuelrmos.podcastapp.R
import com.samuelrmos.podcastapp.data.PodcastWithExtraInfo
import com.samuelrmos.podcastapp.ui.theme.Keyline1
import com.samuelrmos.podcastapp.ui.theme.PodcastAppTheme
import com.samuelrmos.podcastapp.util.*
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Composable
fun Home() {
    val viewModel: HomeViewModel = viewModel()

    val viewState by viewModel.state.collectAsState()

    Surface(Modifier.fillMaxSize()) {
        HomeContent(
            featuredPodcasts = viewState.featuredPodcasts,
            isRefreshing = viewState.refreshing,
            homeCategories = viewState.homeCategories,
            selectedHomeCategory = viewState.selectedHomeCategory,
            onCategorySelected = viewModel::onHomeCategorySelected,
            onPodcastUnfollowed = viewModel::onPodcastUnfollowed,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeContent(
    featuredPodcasts: List<PodcastWithExtraInfo>,
    isRefreshing: Boolean,
    selectedHomeCategory: HomeCategory,
    homeCategories: List<HomeCategory>,
    onCategorySelected: (HomeCategory) -> Unit,
    onPodcastUnfollowed: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val surfaceColor = MaterialTheme.colors.surface
        val dominantColorState = rememberDominantColorState { color ->
            color.constrastAgainst(surfaceColor) >= MinConstrastOfPrimaryVsSurface
        }

        DynamicThemePrimaryColorsFromImage(dominantColorState) {
            val pagerState = rememberPagerState(pageCount = featuredPodcasts.size)

            val selectedImageUrl =
                featuredPodcasts.getOrNull(pagerState.currentPage)?.podcast?.imageUrl

            if (selectedImageUrl != null) {
                LaunchedEffect(selectedImageUrl) {
                    dominantColorState.updateColorsFromImageUrl(selectedImageUrl)
                }
            } else {
                dominantColorState.reset()
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalGradientScrim(
                        color = MaterialTheme.colors.primary.copy(alpha = 0.38f),
                        startYPercentage = 1f,
                        endYPercentage = 0f
                    )
            ) {
                val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)

                Spacer(
                    Modifier
                        .background(appBarColor)
                        .fillMaxWidth()
                        .statusBarsHeight()
                )

                HomeAppBar(
                    backGroundColor = appBarColor,
                    modifier = Modifier.fillMaxWidth()
                )

                if (featuredPodcasts.isNotEmpty()) {
                    Spacer(Modifier.height(16.dp))

                    FollowedPodcasts(
                        items = featuredPodcasts,
                        pagerState = pagerState,
                        onPodcastUnfollowed = onPodcastUnfollowed,
                        modifier = Modifier
                            .padding(start = Keyline1, top = 16.dp, end = Keyline1)
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Spacer(Modifier.height(16.dp))
                }
            }
        }

        if (isRefreshing) {

        }

        if (homeCategories.isNotEmpty()) {
            HomeCategoryTabs(
                categories = homeCategories,
                selectedCategory = selectedHomeCategory,
                onCategorySelected = onCategorySelected
            )
        }
    }

}

@Composable
fun HomeCategoryTabs(
    categories: List<HomeCategory>,
    selectedCategory: HomeCategory,
    onCategorySelected: (HomeCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = categories.indexOfFirst { it == selectedCategory }
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        HomeCategoryTabIndicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
        )
    }

    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = indicator,
        modifier = modifier
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onCategorySelected(category) },
                text = {
                    Text(
                        text = when (category) {
                            HomeCategory.Library -> stringResource(R.string.home_library)
                            HomeCategory.Discover -> stringResource(R.string.home_discover)
                        },
                        style = MaterialTheme.typography.body2
                    )
                }
            )
        }
    }
}

@Composable
fun HomeCategoryTabIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface
) {
    Spacer(
        modifier
            .padding(horizontal = 24.dp)
            .height(4.dp)
            .background(
                color,
                RoundedCornerShape(
                    topStart = CornerSize(100.dp),
                    topEnd = CornerSize(100.dp),
                    bottomEnd = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                )
            )
    )
}

@ExperimentalPagerApi
@Composable
fun FollowedPodcasts(
    items: List<PodcastWithExtraInfo>,
    pagerState: PagerState,
    onPodcastUnfollowed: (String) -> Unit,
    modifier: Modifier
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { page ->
        val (podcast, lastEpisodeDate) = items[page]
        FollowedPodcastCarouselItem(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight(),
            podcastImageUrl = podcast.imageUrl,
            onUnfollowedClick = { onPodcastUnfollowed(podcast.uri) },
            lastEpisodeDate = lastEpisodeDate
        )
    }

}

@Composable
fun HomeAppBar(
    backGroundColor: Color,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = null
                )
                Icon(
                    painter = painterResource(R.drawable.ic_text_logo),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .heightIn(max = 24.dp)
                )
            }
        },
        backgroundColor = backGroundColor,
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.album_search)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(R.string.album_account)
                    )
                }
            }
        },
        modifier = modifier,
    )
}


@Composable
fun FollowedPodcastCarouselItem(
    modifier: Modifier,
    podcastImageUrl: String? = null,
    lastEpisodeDate: OffsetDateTime? = null,
    onUnfollowedClick: () -> Unit
) {
    Column(
        modifier.padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Box(
            Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f)
        ) {
            if (podcastImageUrl != null) {
                CoilImage(
                    data = podcastImageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    loading = { },
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                )
            }

            ToggleFollowPodcastIconButton(
                onClick = onUnfollowedClick,
                isFollowed = true,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }

        if (lastEpisodeDate != null) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = lastUpdated(lastEpisodeDate),
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun lastUpdated(lastEpisodeDate: OffsetDateTime): String {
    val duration = Duration.between(lastEpisodeDate.toLocalDateTime(), LocalDateTime.now())
    val days = duration.toDays().toInt()
    return when {
        days > 28 -> stringResource(R.string.updated_longer)
        days >= 7 -> {
            val weeks = days / 7
            quantityStringResource(R.plurals.updated_weeks_ago, weeks, weeks)
        }
        days > 0 -> quantityStringResource(R.plurals.updated_days_ago, days, days)
        else -> stringResource(R.string.updated_today)
    }
}

private const val MinConstrastOfPrimaryVsSurface = 3f

@Composable
@Preview
fun PreviewHomeContent() {
    PodcastAppTheme {
        HomeContent(
            featuredPodcasts = PreviewPodcastsWithExtraInfo,
            isRefreshing = false,
            homeCategories = HomeCategory.values().asList(),
            selectedHomeCategory = HomeCategory.Discover,
            onCategorySelected = { /*TODO*/ },
            onPodcastUnfollowed = { /*TODO*/ })
    }
}

@Composable
@Preview
fun PreviewPodcastCard() {
    PodcastAppTheme {
        FollowedPodcastCarouselItem(
            modifier = Modifier.size(128.dp),
            onUnfollowedClick = { }
        )
    }
}
