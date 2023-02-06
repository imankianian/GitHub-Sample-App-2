package com.example.samplegithubapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplegithubapp.UiState
import com.example.samplegithubapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _reposState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val reposState: StateFlow<UiState> = _reposState

    init {
        loadUserProfile()
        loadUserRepos()
        getUserProfile()
        getUserRepos()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            repository.loadUserProfile("evanphx")
        }
    }

    private fun loadUserRepos() {
        viewModelScope.launch {
            repository.loadUserRepos("evanphx")
        }
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            repository.getUserProfile("evanphx").collect { localGitHubUser ->
                localGitHubUser?.let {
                    _uiState.value = UiState.Success(localGitHubUser)
                }
            }
        }
    }

    private fun getUserRepos() {
        viewModelScope.launch {
            repository.getRepos().collect { repos ->
                _reposState.value = UiState.Success(repos)
            }
        }
    }
}