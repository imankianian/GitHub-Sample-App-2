package com.example.samplegithubapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.samplegithubapp.R
import com.example.samplegithubapp.TabItem
import com.example.samplegithubapp.UiState
import com.example.samplegithubapp.data.datasource.remote.model.GitHubUser
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(uiState: StateFlow<UiState>) {
    when (val state = uiState.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {

        }
        is UiState.Success -> {
            Pager(gitHubUser = state.gitHubUser)
        }
        is UiState.Error -> {

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(gitHubUser: GitHubUser) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val tabs = listOf(
        TabItem("Profile", R.drawable.ic_user) { UserProfile(gitHubUser = gitHubUser) },
        TabItem("Repos", R.drawable.ic_inventory) { UserRepo() }
    )
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Black,
            contentColor = Color.LightGray,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)

                )
            }) {
            tabs.forEachIndexed { index, tab ->
                LeadingIconTab(
                    icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "") },
                    text = { Text(tab.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        HorizontalPager(state = pagerState, count = tabs.size) { page ->
            tabs[page].screen()
        }
    }
}