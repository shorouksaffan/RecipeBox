package com.example.recipebox.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipebox.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E5BA9)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = "Rating",
            tint = Color.Transparent
        )

    }
}

@Preview
@Composable
fun test(){
    SplashScreen({

    })
}
