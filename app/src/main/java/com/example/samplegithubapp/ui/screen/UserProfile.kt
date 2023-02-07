package com.example.samplegithubapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.samplegithubapp.R
import com.example.samplegithubapp.UiState
import com.example.samplegithubapp.data.datasource.local.model.LocalGitHubUser
import kotlinx.coroutines.flow.StateFlow

@Composable
fun UserProfile(uiState: StateFlow<UiState>) {
    when (val state = uiState.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {

        }
        is UiState.Success<*> -> {
            Profile(user = state.data as LocalGitHubUser)
        }
        is UiState.Error -> {

        }
    }
}

@Composable
fun Profile(user: LocalGitHubUser) {
    Column(modifier = padding20.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp)) {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatarUrl)
                .crossfade(true)
                .build(),
                contentDescription = "user avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape))
            Spacer(modifier = size10)
            Column {
                user.name?.let {
                    Text(text = user.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold)
                }
                Text(text = user.login,
                    fontSize = 25.sp)
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        ProfileInfo(user)
    }
}

@Composable
fun ProfileInfo(user: LocalGitHubUser) {
    Row {
        Spacer(modifier = size10)
        Column {
            user.company?.let {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_company),
                            modifier = size22,
                            contentDescription = "company icon"
                        )
                        Spacer5()
                        Text(text = it,
                            fontSize = profileFontSize,
                            fontWeight = FontWeight.Bold)
                    }
                    Spacer5()
                }
            }
            user.location?.let {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_location),
                            modifier = size22,
                            contentDescription = "location icon"
                        )
                        Spacer5()
                        Text(text = it, fontSize = profileFontSize)
                    }
                    Spacer5()
                }
            }
            user.email?.let {
                Column {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_email),
                            modifier = size22,
                            contentDescription = "email icon"
                        )
                        Spacer5()
                        Text(text = it,
                            fontSize = profileFontSize)
                    }
                    Spacer5()
                }
            }
            user.blog?.let {
                Column {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_blog),
                            modifier = size22,
                            contentDescription = "blog icon"
                        )
                        Spacer5()
                        Text(text = it,
                            fontSize = profileFontSize)
                    }
                    Spacer5()
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_people),
                    modifier = size25,
                    contentDescription = "followers/following icon"
                )
                Spacer(modifier = Modifier.size(3.dp))
                Text(
                    text = user.followers.toString(),
                    fontSize = profileFontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " followers",
                    fontSize = profileFontSize
                )
                Text(
                    text = " . ",
                    fontSize = profileFontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = user.following.toString(),
                    fontSize = profileFontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " following",
                    fontSize = profileFontSize
                )
            }
        }
    }
}