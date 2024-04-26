package br.com.dio.soccernews.ui.news

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
fun NewsScreen(newsViewModel: NewsViewModel) {
    val newsList: List<News> by newsViewModel.newsList.observeAsState(listOf())
    val state: State by newsViewModel.state.observeAsState(State.DONE)
    val errorMessage: String by newsViewModel.errorMessage.observeAsState("")

    if (state == State.ERROR && errorMessage.isNotBlank()) {
        Unit // TODO Snackbar composable error message remove Snackbar legacy in fragment
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state == State.DOING,
        onRefresh = { newsViewModel.getAllNews() }
    )

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        NewsList(newsList = newsList) { newsClicked ->
            newsViewModel.favorite(newsClicked)
        }

        PullRefreshIndicator(
            refreshing = state == State.DOING,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colorScheme.primary
        )
    }
}
