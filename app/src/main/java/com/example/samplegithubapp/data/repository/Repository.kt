package com.example.samplegithubapp.data.repository

import com.example.samplegithubapp.UserProfileResult

interface Repository {

    suspend fun getUserProfile(login: String): UserProfileResult
}