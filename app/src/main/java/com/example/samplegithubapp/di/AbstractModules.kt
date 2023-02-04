package com.example.samplegithubapp.di

import com.example.samplegithubapp.data.datasource.remote.RemoteDataSource
import com.example.samplegithubapp.data.datasource.remote.RemoteDataSourceImpl
import com.example.samplegithubapp.data.repository.Repository
import com.example.samplegithubapp.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModules {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}