package com.example.samplegithubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.samplegithubapp.ui.screen.Bookmarks
import com.example.samplegithubapp.ui.screen.HomeScreen
import com.example.samplegithubapp.ui.theme.SampleGithubAppTheme
import com.example.samplegithubapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleGithubAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.Home.route) {
                        composable(route = Routes.Home.route) {
                            HomeScreen(mainViewModel) {
                                navController.navigate(Routes.Bookmarks.route)
                            }
                        }
                        composable(route = Routes.Bookmarks.route) {
                            Bookmarks(mainViewModel.reposState,
                                { repoId, isFavorite ->
                                    mainViewModel.toggleBookmark(
                                        repoId = repoId,
                                        isFavorite = isFavorite
                                    )
                                }) {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}