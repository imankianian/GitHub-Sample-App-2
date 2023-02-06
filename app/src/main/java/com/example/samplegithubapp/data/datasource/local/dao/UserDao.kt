package com.example.samplegithubapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: LocalGitHubUser)

    @Query("SELECT * FROM users WHERE login = :login")
    fun getUser(login: String): Flow<LocalGitHubUser>
}