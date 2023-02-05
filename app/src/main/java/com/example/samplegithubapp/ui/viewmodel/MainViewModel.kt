package com.example.samplegithubapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplegithubapp.UiState
import com.example.samplegithubapp.RepositoryResult
import com.example.samplegithubapp.data.datasource.remote.model.GitHubUser
import com.example.samplegithubapp.data.datasource.remote.model.RemoteGitHubRepo
import com.example.samplegithubapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _reposState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val reposState: StateFlow<UiState> = _reposState

    init {
        getUserProfile()
        getUserRepos()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            when (val result = repository.getUserProfile(login = "evanphx")) {
                is RepositoryResult.Success<*> -> {
                    _uiState.value = UiState.Success(result.data as GitHubUser)
                }
                is RepositoryResult.Error -> {
                    _uiState.value = UiState.Error(result.message)
                }
            }
        }
    }

    private fun getUserRepos() {
        viewModelScope.launch {
            when (val result = repository.getUserRepos(login = "evanphx")) {
                is RepositoryResult.Success<*> -> {
                    _reposState.value = UiState.Success(result.data as List<RemoteGitHubRepo>)
                }
                is RepositoryResult.Error -> {
                    _reposState.value = UiState.Error(result.message)
                }
            }
        }
    }
}