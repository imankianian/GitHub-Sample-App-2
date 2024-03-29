package com.example.samplegithubapp.di

import android.app.Application
import androidx.room.Room
import com.example.samplegithubapp.data.datasource.local.db.GitHubDatabase
import com.example.samplegithubapp.data.datasource.remote.service.GitHubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Singleton
    fun provideGitHubApiService(): GitHubApi = Retrofit
        .Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubApi::class.java)

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideGitHubDatabase(application: Application): GitHubDatabase = Room
        .databaseBuilder(application, GitHubDatabase::class.java, "github_database")
        .build()
}