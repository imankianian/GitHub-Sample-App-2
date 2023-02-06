package com.example.samplegithubapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepos(repos: List<LocalGitHubRepo>)

    @Query("SELECT * FROM repos")
    fun getRepos(): Flow<List<LocalGitHubRepo>>
}