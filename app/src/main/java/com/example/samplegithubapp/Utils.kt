package com.example.samplegithubapp

import androidx.compose.runtime.Composable

sealed interface NetworkResult {
    data class Success<T: Any>(val data: T): NetworkResult
    data class Error(val code: Int?, val message: String?): NetworkResult
    data class Failure(val message: String?): NetworkResult
}

sealed interface RepositoryResult {
    data class Success<T: Any>(val data: T): RepositoryResult
    data class Error(val message: String?): RepositoryResult
}

sealed interface UiState {
    object Loading: UiState
    data class Success<T: Any>(val data: T): UiState
    data class Error(val message: String?): UiState
}

sealed class Routes(val route: String) {
    object Home: Routes("home")
}

data class TabItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit
)