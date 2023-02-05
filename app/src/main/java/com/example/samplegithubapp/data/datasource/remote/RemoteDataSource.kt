package com.example.samplegithubapp.data.datasource.remote

import com.example.samplegithubapp.NetworkResult

interface RemoteDataSource {

    suspend fun getUserProfile(login: String): NetworkResult
    suspend fun getUserRepos(login: String): NetworkResult
}