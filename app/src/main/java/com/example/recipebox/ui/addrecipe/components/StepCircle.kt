package com.example.recipebox.ui.addrecipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val StepTrack = Color(0xFF1A1A1A)

@Composable
fun StepCircle(number: String) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(StepTrack),
        contentAlignment = Alignment.Center
    ) {
        Text(number, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}

