package com.example.samplegithubapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.samplegithubapp.R
import com.example.samplegithubapp.TabItem
import com.example.samplegithubapp.ui.viewmodel.MainViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(text = stringResource(id = R.string.app_name),
                            modifier = Modifier.weight(1f))
                        Image(painter = painterResource(id = R.drawable.ic_bookmark_menu),
                            contentDescription = "bookmark icon",
                            modifier = Modifier.padding(end = 10.dp))
                    }
                },
                backgroundColor = Color.Black,
                contentColor = Color.White
            )
        }, content = {
            Column {
                Pager(viewModel)
            }
        }
    )
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