package br.com.dio.soccernews.ui.components.bottom_nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.dio.soccernews.R

sealed class Screen(
    val route: String,
    @StringRes var stringId: Int,
    @DrawableRes val iconId: Int
) {

    data object News : Screen(
        "news",
        R.string.title_news,
        R.drawable.ic_soccer
    )

    data object Favorites : Screen(
        "favorites",
        R.string.title_favorites,
        R.drawable.ic_favorite
    )

}
