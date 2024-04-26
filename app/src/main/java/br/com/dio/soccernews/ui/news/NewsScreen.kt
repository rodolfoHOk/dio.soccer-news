package br.com.dio.soccernews.ui.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import br.com.dio.soccernews.domain.model.News
import br.com.dio.soccernews.ui.components.NewsList

@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {
    val newsList: List<News> by newsViewModel.newsList.observeAsState(listOf())

    NewsList(newsList = newsList) { newsClicked ->
        newsViewModel.favorite(newsClicked)
    }
}
