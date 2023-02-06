package com.example.samplegithubapp.data.repository

import com.example.samplegithubapp.RepositoryResult
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun loadUserProfile(login: String)
    fun getUserProfile(login: String): Flow<LocalGitHubUser?>
    suspend fun getUserRepos(login: String): RepositoryResult
}