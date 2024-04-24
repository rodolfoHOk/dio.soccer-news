package br.com.dio.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dio.soccernews.domain.model.News

class NewsViewModel : ViewModel() {

    private val _news = MutableLiveData<List<News>>().apply {
        // TODO remove news mock
        value = listOf(
            News(
                title = "Ferroviária tem Desfalque Importante",
                headline = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sit amet ligula porttitor, gravida augue ut, accumsan elit."
            ),
            News(
                title = "Ferrinha Joga no Sábado",
                headline = "Etiam hendrerit, turpis vitae laoreet tempor, libero tellus condimentum urna, sed finibus lorem nulla eget ex."
            ),
            News(
                title = "Copa do Mundo Feminina está Terminando",
                headline = "Suspendisse vel lacinia erat. Duis bibendum dictum metus eget luctus. Aliquam quis metus nibh."
            )
        )
    }
    val news: LiveData<List<News>> = _news

}
