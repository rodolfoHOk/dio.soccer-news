package br.com.dio.soccernews.ui.components.bottom_nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.dio.soccernews.ui.commons.state.TopBarState
import br.com.dio.soccernews.ui.favorites.FavoritesScreen
import br.com.dio.soccernews.ui.favorites.FavoritesViewModel
import br.com.dio.soccernews.ui.news.NewsScreen
import br.com.dio.soccernews.ui.news.NewsViewModel

@Composable
fun AppNavHost(
    navController: NavController,
    modifier: Modifier,
    onComposing: (topBarState: TopBarState) -> Unit
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.News.route,
        modifier = modifier
    ) {

        composable(Screen.News.route) {
            val newsViewModel = hiltViewModel<NewsViewModel>()
            NewsScreen(newsViewModel = newsViewModel, onComposing)
        }

        composable(Screen.Favorites.route) {
            val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
            FavoritesScreen(favoritesViewModel = favoritesViewModel, onComposing)
        }

    }
}
