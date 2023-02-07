package com.example.samplegithubapp.data.datasource.local

import android.util.Log
import com.example.samplegithubapp.TAG
import com.example.samplegithubapp.data.datasource.local.db.GitHubDatabase
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import com.example.samplegithubapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
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
            repos.forEach {
                try {
                    val result = gitHubDatabase.repoDao.updateRepo(it.id, it.name, it.lastUpdate, it.stars, it.language)
                    if (result <= 0) {
                        Log.d(TAG, "Inserting repo into DB with Id = ${it.id}")
                        gitHubDatabase.repoDao.addRepo(it)
                    } else {
                        Log.d(TAG, "Repo Already existing. Updating repo with Id = ${it.id}")
                    }
                } catch (exception: Exception) {
                    Log.d(TAG, exception.message.toString())
                }
            }
        }
    }

    override fun getRepos() = gitHubDatabase.repoDao.getRepos().flowOn(dispatcher)

    override suspend fun updateRepo(id: Int, isFavorite: Boolean) {
        withContext(dispatcher) {
            gitHubDatabase.repoDao.updateRepo(id, isFavorite)
        }
    }
}