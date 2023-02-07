package com.example.samplegithubapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.samplegithubapp.UiState
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Bookmarks(reposState: StateFlow<UiState>,
              onBookMarkClicked: (repoId: Int, isFavorite: Boolean) -> Unit,
              onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(text = "Bookmarks",
                            modifier = Modifier.weight(1f))
                    }
                },
                backgroundColor = Color.Black,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "back icon")
                    }
                }
            )
        }, content = {
            Column {
                DisplayBookmarks(reposState = reposState, onBookMarkClicked = onBookMarkClicked)
            }
        }
    )
}

@Composable
fun DisplayBookmarks(reposState: StateFlow<UiState>, onBookMarkClicked: (repoId: Int, isFavorite: Boolean) -> Unit) {
    when (val state = reposState.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {

        }
        is UiState.Success<*> -> {
            ReposListScreen(repos = (state.data as List<LocalGitHubRepo>).filter {
                it.isFavorite
            }, onBookMarkClicked)
        }
        is UiState.Error -> {

        }
    }
}