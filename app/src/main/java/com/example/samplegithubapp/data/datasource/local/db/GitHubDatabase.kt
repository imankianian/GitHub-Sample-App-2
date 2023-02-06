package com.example.samplegithubapp.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.samplegithubapp.data.datasource.local.dao.RepoDao
import com.example.samplegithubapp.data.datasource.local.dao.UserDao
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser

@Database(entities = [LocalGitHubUser::class, LocalGitHubRepo::class], version = 1, exportSchema = false)
abstract class GitHubDatabase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repoDao: RepoDao
}