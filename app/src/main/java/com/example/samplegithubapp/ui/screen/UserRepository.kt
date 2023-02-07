package com.example.samplegithubapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.samplegithubapp.R
import com.example.samplegithubapp.UiState
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubRepo
import kotlinx.coroutines.flow.StateFlow

@Composable
fun UserRepo(reposState: StateFlow<UiState>, onBookMarkClicked: (repoId: Int, isFavorite: Boolean) -> Unit) {
    when (val state = reposState.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {

        }
        is UiState.Success<*> -> {
            ReposListScreen(repos = state.data as List<LocalGitHubRepo>, onBookMarkClicked)
        }
        is UiState.Error -> {

        }
    }
}

@Composable
fun ReposListScreen(repos: List<LocalGitHubRepo>, onBookMarkClicked: (repoId: Int, isFavorite: Boolean) -> Unit) {
    LazyColumn(modifier = Modifier.background(Color.White)) {
        items(repos) { repo ->
            Spacer15()
            RepoCard(repo, onBookMarkClicked)
            Spacer15()
            lazyColumnDivider()
        }
    }
}

@Composable
fun RepoCard(repo: LocalGitHubRepo, onBookMarkClicked: (repoId: Int, isFavorite: Boolean) -> Unit) {
    Card {
        Row(modifier = cardRowModifier) {
            Spacer10()
            DisplayRepo(repo, onBookMarkClicked)
        }
    }
}

@Composable
fun DisplayRepo(repo: LocalGitHubRepo, onBookMarkClicked: (repoId: Int, isFavorite: Boolean) -> Unit) {
    Row {
        Column(modifier = Modifier.weight(1f)) {
            RepoName(name = repo.name)
            RepoUpdateDate(date = repo.lastUpdate)
            Spacer5()
            Row(verticalAlignment = Alignment.CenterVertically) {
                RepoStar(count = repo.stars)
                Spacer15()
                RepoLanguage(language = repo.language ?: "Unknown")
            }
        }
        BookmarkImage(repo = repo, onBookMarkClicked = onBookMarkClicked)
    }
}

@Composable
fun BookmarkImage(repo: LocalGitHubRepo, onBookMarkClicked: (repoId: Int, isFavorite: Boolean) -> Unit) {
    if (repo.isFavorite) {
        Image(painter = painterResource(id = R.drawable.ic_bookmarked),
            contentDescription = "bookmarked icon",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 10.dp)
                .clickable {
                    onBookMarkClicked(repo.id, !repo.isFavorite)
                })
    } else {
        Image(painter = painterResource(id = R.drawable.ic_not_bookmarked),
            contentDescription = "not_bookmarked icon",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 10.dp)
                .clickable {
                    onBookMarkClicked(repo.id, !repo.isFavorite)
                })
    }
}

@Composable
fun RepoName(name: String) {
    Text(text = name,
        color = Color.Black,
        fontSize = 17.sp,
        textAlign = TextAlign.Start
    )
}

@Composable
fun RepoUpdateDate(date: String) {
    Text(text = date,
        color = Color.LightGray,
        fontSize = 10.sp,
        textAlign = TextAlign.Start
    )
}

@Composable
fun RepoStar(count: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "stars",
            modifier = height20)
        Spacer5()
        Text(text = count.toString(),
            color = Color.Gray,
            fontSize = 15.sp,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun RepoLanguage(language: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painterResource(id = R.drawable.ic_language),
            contentDescription = "programming language",
            modifier = height20)

    }
    Spacer5()
    Text(text = language,
        color = Color.Gray,
        fontSize = 15.sp,
        textAlign = TextAlign.Start
    )
}