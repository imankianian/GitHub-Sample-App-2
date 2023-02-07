package com.example.samplegithubapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.samplegithubapp.R
import com.example.samplegithubapp.TabItem
import com.example.samplegithubapp.ui.viewmodel.MainViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Pager(viewModel = viewModel)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(viewModel: MainViewModel) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val tabs = listOf(
        TabItem("Profile", R.drawable.ic_user) { UserProfile(viewModel.uiState) },
        TabItem("Repos", R.drawable.ic_inventory) { UserRepo(viewModel.reposState) {
            repoId, isFavorite -> viewModel.toggleBookmark(repoId = repoId, isFavorite = isFavorite)
        } }
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