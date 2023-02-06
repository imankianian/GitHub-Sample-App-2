package com.example.samplegithubapp.data.datasource.local

import com.example.samplegithubapp.data.datasource.local.db.GitHubDatabase
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import com.example.samplegithubapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val gitHubDatabase: GitHubDatabase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher): LocalDataSource {

    override suspend fun addUser(user: LocalGitHubUser) {
        withContext(dispatcher) {
            gitHubDatabase.userDao.addUser(user)
        }
    }

    override fun getUser(login: String) = gitHubDatabase.userDao.getUser(login).flowOn(dispatcher)

    override suspend fun addRepos(repos: List<LocalGitHubRepo>) {
        withContext(dispatcher) {
            gitHubDatabase.repoDao.addRepos(repos)
        }
    }

    override fun getRepos() = gitHubDatabase.repoDao.getRepos().flowOn(dispatcher)
}