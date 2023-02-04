package com.example.samplegithubapp

import com.example.samplegithubapp.data.datasource.remote.model.GitHubUser

sealed interface NetworkResult {
    data class Success(val gitHubUser: GitHubUser): NetworkResult
    data class Error(val code: Int?, val message: String?): NetworkResult
    data class Failure(val message: String?): NetworkResult
}