package com.example.samplegithubapp.data.datasource.local

import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun addUser(user: LocalGitHubUser)
    fun getUser(login: String): Flow<LocalGitHubUser?>
}