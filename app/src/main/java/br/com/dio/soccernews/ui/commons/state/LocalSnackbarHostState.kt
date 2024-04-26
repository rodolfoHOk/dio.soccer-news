package br.com.dio.soccernews.ui.commons.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalSnackbarHostState = (compositionLocalOf<SnackbarHostState> {
    error("No Snackbar Host State")
})
