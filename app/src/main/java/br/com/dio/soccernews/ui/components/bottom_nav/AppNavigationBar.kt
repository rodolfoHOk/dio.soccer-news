package br.com.dio.soccernews.ui.components.bottom_nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.News,
        Screen.Favorites
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            val isSelectedItem = currentDestination?.hierarchy
                ?.any { it.route == screen.route } == true

            NavigationBarItem(
                selected = isSelectedItem,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(screen.iconId),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(screen.stringId)) }
            )
        }
    }
}

@Preview
@Composable
fun AppNavigationBarPreview() {
    val navController = rememberNavController()

    AppNavigationBar(navController)
}
