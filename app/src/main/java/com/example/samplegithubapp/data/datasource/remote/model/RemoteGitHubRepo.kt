package com.example.samplegithubapp.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteGitHubRepo(
    val id:Int,
    val name: String,
    @SerializedName("updated_at") val lastUpdate: String,
    @SerializedName("stargazers_count") val stars: Int,
    val language: String?
)