package com.example.samplegithubapp.data.datasource.local

import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun addUser(user: LocalGitHubUser)
    fun getUser(login: String): Flow<LocalGitHubUser?>
    suspend fun addRepos(repos: List<LocalGitHubRepo>)
    fun getRepos(): Flow<List<LocalGitHubRepo>>
    suspend fun updateRepo(id:Int, isFavorite: Boolean)
}