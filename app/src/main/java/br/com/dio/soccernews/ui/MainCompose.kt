package br.com.dio.soccernews.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.dio.soccernews.ui.commons.state.LocalSnackbarHostState
import br.com.dio.soccernews.ui.commons.state.TopBarState
import br.com.dio.soccernews.ui.components.bottom_nav.AppNavHost
import br.com.dio.soccernews.ui.components.bottom_nav.AppNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose() {
    val navController = rememberNavController()
    var topBarState by remember { mutableStateOf(TopBarState()) }
    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(
        value = LocalSnackbarHostState provides snackbarHostState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = topBarState.title) })
            },
            bottomBar = {
                AppNavigationBar(navController = navController)
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { paddingValues ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            ) {
                topBarState = it
            }
        }
    }
}
