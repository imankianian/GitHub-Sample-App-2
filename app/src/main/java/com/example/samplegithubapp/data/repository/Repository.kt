package com.example.samplegithubapp.data.repository

import com.example.samplegithubapp.RepositoryResult

interface Repository {

    suspend fun getUserProfile(login: String): RepositoryResult
    suspend fun getUserRepos(login: String): RepositoryResult
}