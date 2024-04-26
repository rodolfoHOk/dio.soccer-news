package br.com.dio.soccernews.ui.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.dio.soccernews.domain.model.News
import br.com.dio.soccernews.ui.commons.state.State
import br.com.dio.soccernews.ui.components.NewsList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel) {
    val favoritesNews: List<News> by favoritesViewModel.favoritesNewsList.observeAsState(listOf())
    val state: State by favoritesViewModel.state.observeAsState(State.DONE)
    val errorMessage: String by favoritesViewModel.errorMessage.observeAsState("")

    if (state == State.ERROR && errorMessage.isNotBlank()) {
        Unit // TODO Snackbar error message
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state == State.DOING,
        onRefresh = { favoritesViewModel.findAllFavorites() }
    )

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        NewsList(newsList = favoritesNews) { newsClicked ->
            favoritesViewModel.removeFavorite(newsClicked)
        }

        PullRefreshIndicator(
            refreshing = state == State.DOING,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colorScheme.primary
        )
    }
}
