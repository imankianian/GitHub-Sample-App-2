package com.example.samplegithubapp

import androidx.compose.runtime.Composable
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import java.text.SimpleDateFormat

const val TAG = "SGA ===>"

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
    object Bookmarks: Routes("bookmarks")
}

data class TabItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit
)

fun List<LocalGitHubRepo>.convert(): List<LocalGitHubRepo> = map {
    LocalGitHubRepo(
        id = it.id,
        name = it.name,
        lastUpdate = it.lastUpdate.formattedDate(),
        stars = it.stars,
        language = it.language,
        isFavorite = it.isFavorite
    )
}

fun String.formattedDate(): String {
    return try {
        val oldFormat = SimpleDateFormat("yyyy-mm-dd")
        val date = oldFormat.parse(this)
        val newFormat = SimpleDateFormat("mm-dd-yyyy")
        newFormat.format(date)
    } catch (e: Exception) {
        this
    }
}