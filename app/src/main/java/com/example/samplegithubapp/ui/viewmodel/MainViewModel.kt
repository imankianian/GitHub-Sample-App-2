package com.example.samplegithubapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplegithubapp.UiState
import com.example.samplegithubapp.UserProfileResult
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

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            when (val result = repository.getUserProfile(login = "evanphx")) {
                is UserProfileResult.Success -> {
                    _uiState.value = UiState.Success(result.gitHubUser)
                }
                is UserProfileResult.Error -> {
                    _uiState.value = UiState.Error(result.message)
                }
            }
        }
    }
}