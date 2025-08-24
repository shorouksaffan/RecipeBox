package com.example.recipebox.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.recipebox.R
import kotlinx.coroutines.delay
val AQRADA = FontFamily(
    Font(R.font.aqrada, FontWeight.Normal)
)

data class OnboardingPage(
    val background: Color,
    val images: List<Int>, // drawable ids
    val text: String,
    val showGoButton: Boolean = false
)

@Composable
fun OnboardingScreen(/*onFinish: () -> Unit*/) {
    val pages = listOf(
        OnboardingPage(
            background = Color(0xFF304FFE),
            images = listOf(R.drawable.onboarding1_food1, R.drawable.onboarding1_food2, R.drawable.onboarding1_food3),
            text = "Your personal guide to be a chef"
        ),
        OnboardingPage(
            background = Color(0xFFFF3D00),
            images = listOf(R.drawable.onboarding2_food1, R.drawable.onboarding2_food2, R.drawable.onboarding2_food3),
            text = "Share the Love, Share the Recipe"
        ),
        OnboardingPage(
            background = Color(0xFFFFFF00),
            images = listOf(R.drawable.onboarding3_food1, R.drawable.onboarding3_food2, R.drawable.onboarding3_food3),
            text = "Foodify Your Global Kitchen",
            showGoButton = true
        )
    )

    var currentPage by remember { mutableStateOf(0) }

    LaunchedEffect(currentPage) {
        if (currentPage < pages.lastIndex) {
            delay(3000)
            currentPage++
        }
    }

    val page = pages[currentPage]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(page.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                page.images.forEach { img ->
                    Image(
                        painter = painterResource(id = img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp) // Increased image size
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy((-20).dp)
            ) {
                Text(
                    text = page.text,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = AQRADA,
                    lineHeight = 1.9.em,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Black, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
                        .padding(start = 40.dp, top = 16.dp, end = 40.dp, bottom = 30.dp)
                )

                if (page.showGoButton) {
                    Button(
                        onClick = { /* TODO: Implement navigation logic here */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = CircleShape
                    ) {
                        Text(text = "Go", color = Color.Black)
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.White, CircleShape)
                            .border(2.dp, Color.Black, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(42.dp),
                            color = Color(0xFFFFFFFF),
                            trackColor = Color.Transparent,
                            strokeWidth = 4.dp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewOnboarding() {
    OnboardingScreen(/*onFinish = {}*/)
}