package br.com.dio.soccernews.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.dio.soccernews.domain.model.News

@Composable
fun NewsList(newsList: List<News>, onFavorite: (news: News) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(newsList) { news ->
            NewsItem(news = news, onFavorite = onFavorite)
        }
    }
}

@Preview
@Composable
fun NewsListPreview() {

    val newsList = listOf(
        News(
            id = 1,
            title = "Você sabia?",
            headline = "Mulheres colecionam conquistas históricas no futebol; veja oito delas neste 8 de março.",
            image = "https://rodolfohok.github.io/dio.soccer-news-api/images/1.jpg",
            link = "https://ge.globo.com/pe/futebol/noticia/2022/03/08/voce-sabia-mulheres-colecionam-conquistas-historicas-no-futebol-veja-oito-delas-neste-8-de-marco.ghtml",
            favorite = false
        ),
        News(
            id = 2,
            title = "Por que vou parar de sonhar?",
            headline = "Em carta ao filho, Cristiane recorda infância dura e ambições: Por que vou parar de sonhar?",
            image = "https://rodolfohok.github.io/dio.soccer-news-api/images/2.jpg",
            link = "https://ge.globo.com/pe/futebol/noticia/2022/03/02/em-carta-ao-filho-cristiane-recorda-infancia-dura-e-ambicoes-por-que-vou-parar-de-sonhar.ghtml",
            favorite = true
        ),
    )

    NewsList(newsList = newsList, onFavorite = { newsClicked ->
        Log.d("App -> ", "favorite click on news with id: ${newsClicked.id}")
    })
}
