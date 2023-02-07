package com.example.samplegithubapp.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class LocalGitHubRepo(

    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name: String,
    @ColumnInfo(name = "updated_at") val lastUpdate: String,
    @ColumnInfo(name ="stargazers_count") val stars: Int,
    val language: String?,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false
)