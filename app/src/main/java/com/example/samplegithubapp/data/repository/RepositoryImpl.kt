package com.example.samplegithubapp.data.repository

import com.example.samplegithubapp.NetworkResult
import com.example.samplegithubapp.RepositoryResult
import com.example.samplegithubapp.data.datasource.remote.RemoteDataSource
import com.example.samplegithubapp.data.datasource.remote.model.GitHubUser
import com.example.samplegithubapp.data.datasource.remote.model.RemoteGitHubRepo
import com.example.samplegithubapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher): Repository {

    override suspend fun getUserProfile(login: String) = withContext(dispatcher) {
        when (val result = remoteDataSource.getUserProfile(login)) {
            is NetworkResult.Success<*> -> {
                return@withContext RepositoryResult.Success(result.data as GitHubUser)
            }
            is NetworkResult.Error -> {
                return@withContext RepositoryResult.Error("${result.code}, ${result.message}")
            }
            is NetworkResult.Failure -> {
                return@withContext RepositoryResult.Error(result.message)
            }
        }
    }

    override suspend fun getUserRepos(login: String) = withContext(dispatcher) {
        when (val result = remoteDataSource.getUserRepos(login)) {
            is NetworkResult.Success<*> -> {
                return@withContext RepositoryResult.Success(result.data as List<RemoteGitHubRepo>)
            }
            is NetworkResult.Error -> {
                return@withContext RepositoryResult.Error("${result.code}, ${result.message}")
            }
            is NetworkResult.Failure -> {
                return@withContext RepositoryResult.Error(result.message)
            }
        }
    }
}