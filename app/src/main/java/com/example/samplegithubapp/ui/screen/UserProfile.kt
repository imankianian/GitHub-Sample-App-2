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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.samplegithubapp.R
import com.example.samplegithubapp.data.datasource.remote.model.GitHubUser

@Composable
fun UserProfile(gitHubUser: GitHubUser) {
    Column(modifier = padding20) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp)) {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(gitHubUser.avatarUrl)
                .crossfade(true)
                .build(),
                contentDescription = "user avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape))
            Spacer(modifier = size10)
            Column {
                gitHubUser.name?.let {
                    Text(text = gitHubUser.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold)
                }
                Text(text = gitHubUser.login,
                    fontSize = 25.sp)
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        ProfileInfo(gitHubUser)
    }
}

@Composable
fun ProfileInfo(gitHubUser: GitHubUser) {
    Row {
        Spacer(modifier = size10)
        Column {
            gitHubUser.company?.let {
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
            }
            Spacer5()
            gitHubUser.location?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        modifier = size22,
                        contentDescription = "location icon"
                    )
                    Spacer5()
                    Text(text = it, fontSize = profileFontSize)
                }
            }
            Spacer5()
            gitHubUser.email?.let {
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
            }
            Spacer5()
            gitHubUser.blog?.let {
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
            }
            Spacer5()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_people),
                    modifier = size25,
                    contentDescription = "followers/following icon"
                )
                Spacer5()
                Text(
                    text = gitHubUser.followers.toString(),
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
                    text = gitHubUser.following.toString(),
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

private val profileFontSize = 15.sp
private val size5 = Modifier.size(5.dp)
private val size10 = Modifier.size(10.dp)
private val size22 = Modifier.size(22.dp)
private val size25 = Modifier.size(25.dp)
private val padding20 = Modifier.padding(20.dp)

@Composable
private fun Spacer5() {
    Spacer(modifier = size5)
}