package com.example.samplegithubapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
private val lazyColumnModifier = Modifier
    .fillMaxWidth()
    .height(1.dp)
val cardRowModifier = Modifier
    .fillMaxWidth()
    .background(Color.White)

@Composable
fun Spacer5() {
    Spacer(modifier = size5)
}

@Composable
fun Spacer10() {
    Spacer(modifier = size10)
}

@Composable
fun Spacer15() {
    Spacer(modifier = size15)
}

@Composable
fun lazyColumnDivider() {
    Divider(color = MaterialTheme.colorScheme.onSurface.copy(0.1f),
        modifier = lazyColumnModifier)
}