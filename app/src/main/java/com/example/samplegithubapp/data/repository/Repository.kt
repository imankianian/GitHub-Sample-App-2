package com.example.samplegithubapp.data.repository

import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun loadUserProfile(login: String)
    fun getUserProfile(login: String): Flow<LocalGitHubUser?>
    suspend fun loadUserRepos(login: String)
    fun getRepos(): Flow<List<LocalGitHubRepo>>
    suspend fun updateRepo(id: Int, isFavorite: Boolean)
}