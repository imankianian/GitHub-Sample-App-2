package com.example.samplegithubapp.data.repository

import com.example.samplegithubapp.NetworkResult
import com.example.samplegithubapp.UserProfileResult
import com.example.samplegithubapp.data.datasource.remote.RemoteDataSource
import com.example.samplegithubapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher): Repository {

    override suspend fun getUserProfile(login: String) = withContext(dispatcher) {
        when (val result = remoteDataSource.getUserProfile(login)) {
            is NetworkResult.Success -> {
                return@withContext UserProfileResult.Success(result.gitHubUser)
            }
            is NetworkResult.Error -> {
                return@withContext UserProfileResult.Error("${result.code}, ${result.message}")
            }
            is NetworkResult.Failure -> {
                return@withContext UserProfileResult.Error(result.message)
            }
        }
    }
}