package com.example.samplegithubapp.data.repository

import com.example.samplegithubapp.NetworkResult
import com.example.samplegithubapp.data.datasource.local.LocalDataSource
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import com.example.samplegithubapp.data.datasource.remote.RemoteDataSource
import com.example.samplegithubapp.data.datasource.remote.model.RemoteGitHubUser
import com.example.samplegithubapp.data.datasource.remote.model.RemoteGitHubRepo
import com.example.samplegithubapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val localDataSource: LocalDataSource): Repository {

    override suspend fun loadUserProfile(login: String) {
        withContext(dispatcher) {
            when (val result = remoteDataSource.getUserProfile(login)) {
                is NetworkResult.Success<*> -> {
                    val remoteGitHubUser = result.data as RemoteGitHubUser
                    val localGitHubUser = LocalGitHubUser(remoteGitHubUser.id, remoteGitHubUser.login,
                        remoteGitHubUser.name, remoteGitHubUser.avatarUrl, remoteGitHubUser.followers,
                        remoteGitHubUser.following, remoteGitHubUser.company, remoteGitHubUser.location,
                        remoteGitHubUser.email, remoteGitHubUser.blog)
                    localDataSource.addUser(localGitHubUser)
                }
                else -> {
                    delay(5000)
                    loadUserProfile(login)
                }
            }
        }
    }

    override fun getUserProfile(login: String) = localDataSource.getUser(login).flowOn(dispatcher)

    override suspend fun loadUserRepos(login: String) {
        withContext(dispatcher) {
            when (val result = remoteDataSource.getUserRepos(login)) {
                is NetworkResult.Success<*> -> {
                    val remoteRepos = result.data as List<RemoteGitHubRepo>
                    val localRepos = remoteRepos.map { remote ->
                        LocalGitHubRepo(remote.id, remote.name, remote.lastUpdate, remote.stars,
                            remote.language)
                    }
                    localDataSource.addRepos(localRepos)
                }
                else -> {
                    delay(5000)
                    loadUserRepos(login)
                }
            }
        }
    }

    override fun getRepos() = localDataSource.getRepos().flowOn(dispatcher)

    override suspend fun updateRepo(id: Int, isFavorite: Boolean) {
        withContext(dispatcher) {
            localDataSource.updateRepo(id, isFavorite)
        }
    }
}