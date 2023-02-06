package com.example.samplegithubapp.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class LocalGitHubUser(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val login: String,
    val name: String?,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    val followers: Int,
    val following: Int,
    val company: String?,
    val location: String?,
    val email: String?,
    val blog: String?
)
