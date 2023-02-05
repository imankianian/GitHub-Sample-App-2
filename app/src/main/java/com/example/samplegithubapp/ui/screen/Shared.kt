package com.example.samplegithubapp.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val profileFontSize = 15.sp
val size5 = Modifier.size(5.dp)
val size10 = Modifier.size(10.dp)
val size15 = Modifier.size(15.dp)
val height20 = Modifier.height(20.dp)
val size22 = Modifier.size(22.dp)
val size25 = Modifier.size(25.dp)
val padding20 = Modifier.padding(20.dp)

@Composable
fun Spacer5() {
    Spacer(modifier = size5)
}

@Composable
fun Spacer15() {
    Spacer(modifier = size15)
}