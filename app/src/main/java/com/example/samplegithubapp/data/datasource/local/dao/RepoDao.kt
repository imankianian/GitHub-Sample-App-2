package com.example.samplegithubapp.data.datasource.local.dao

import androidx.room.*
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Insert
    suspend fun addRepo(repos: LocalGitHubRepo)

    @Query("SELECT * FROM repos")
    fun getRepos(): Flow<List<LocalGitHubRepo>>

    @Query("UPDATE repos SET name = :name," +
                           " updated_at = :lastUpdate," +
                           " stargazers_count = :stars," +
                           " language = :language WHERE id = :id")
    suspend fun updateRepo(id: Int, name: String, lastUpdate: String, stars: Int, language: String?): Int

    @Query("UPDATE repos SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateRepo(id: Int, isFavorite: Boolean)
}