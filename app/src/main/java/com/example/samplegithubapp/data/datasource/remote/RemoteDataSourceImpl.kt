package com.example.samplegithubapp.data.datasource.remote

import com.example.samplegithubapp.NetworkResult
import com.example.samplegithubapp.data.datasource.remote.service.GitHubApi
import com.example.samplegithubapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val gitHubApi: GitHubApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher): RemoteDataSource {

    override suspend fun getUserProfile(login: String) = withContext(dispatcher) {
        val response = gitHubApi.getUserProfile(login)
        return@withContext try {
            if (response != null && response.isSuccessful) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.code(), response.message())
            }
        } catch (exception: Exception) {
            NetworkResult.Failure(exception.message)
        }
    }

    override suspend fun getUserRepos(login: String) = withContext(dispatcher) {
        val response = gitHubApi.getUserRepos(login)
        return@withContext try {
            if (response != null && response.isSuccessful) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.code(), response.message())
            }
        } catch (exception: Exception) {
            NetworkResult.Failure(exception.message)
        }
    }
}