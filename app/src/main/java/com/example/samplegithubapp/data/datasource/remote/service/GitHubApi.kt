package com.example.samplegithubapp.data.datasource.remote.service

import com.example.samplegithubapp.data.datasource.remote.model.GitHubUser
import com.example.samplegithubapp.data.datasource.remote.model.RemoteGitHubRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{login}")
    suspend fun getUserProfile(@Path("login") login: String): Response<GitHubUser>

    @GET("users/{login}/repos")
    suspend fun getUserRepos(@Path("login") login: String): Response<List<RemoteGitHubRepo>>
}