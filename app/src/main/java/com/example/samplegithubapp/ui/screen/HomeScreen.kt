package com.example.samplegithubapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.samplegithubapp.UiState
import com.example.samplegithubapp.data.datasource.remote.model.GitHubUser
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(uiState: StateFlow<UiState>) {
    when (val state = uiState.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {

        }
        is UiState.Success -> {
            UserProfile(gitHubUser = state.gitHubUser)
        }
        is UiState.Error -> {

        }
    }
}

@Composable
fun UserProfile(gitHubUser: GitHubUser) {

}