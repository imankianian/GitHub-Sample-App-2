package com.example.samplegithubapp.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val id: Int,
    val login: String,
    val name: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val followers: Int,
    val following: Int,
    val company: String,
    val location: String,
    val email: String,
    val blog: String
)
