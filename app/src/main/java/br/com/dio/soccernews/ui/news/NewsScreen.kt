package br.com.dio.soccernews.ui.news

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.dio.soccernews.R
import br.com.dio.soccernews.domain.model.News
import br.com.dio.soccernews.ui.commons.state.ActionState
import br.com.dio.soccernews.ui.commons.state.LocalSnackbarHostState
import br.com.dio.soccernews.ui.commons.state.TopBarState
import br.com.dio.soccernews.ui.components.NewsList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel,
    onComposing: (topBarState: TopBarState) -> Unit
) {

    val screenTitle = stringResource(id = R.string.title_news)
    LaunchedEffect(key1 = Unit) {
        onComposing(TopBarState(title = screenTitle))
    }

    val newsList: List<News> by newsViewModel.newsList.observeAsState(listOf())
    val actionState: ActionState by newsViewModel.actionState.observeAsState(ActionState.DONE)
    val errorMessage: String by newsViewModel.errorMessage.observeAsState("")

    if (actionState == ActionState.ERROR && errorMessage.isNotBlank()) {
        val snackbarHostState = LocalSnackbarHostState.current
        LaunchedEffect(key1 = Unit) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Long
            )
        }
    }

    val pullRefreshActionState = rememberPullRefreshState(
        refreshing = actionState == ActionState.DOING,
        onRefresh = { newsViewModel.getAllNews() }
    )

    Box(modifier = Modifier.pullRefresh(pullRefreshActionState)) {
        NewsList(newsList = newsList) { newsClicked ->
            newsViewModel.favorite(newsClicked)
        }

        PullRefreshIndicator(
            refreshing = actionState == ActionState.DOING,
            state = pullRefreshActionState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colorScheme.primary
        )
    }

}
