package com.example.recipebox.ui.addrecipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipebox.ui.theme.White



@Composable
fun ProgressIndicator() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Step 1 - Completed
        StepItem(number = "1", text = "Recipe Information", isSelected = false)

        // Divider line
        Spacer(
            modifier = Modifier
                .width(40.dp)
                .height(1.dp)
                .background(Color.Gray)
        )

        // Step 2 - Current
        StepItem(number = "2", text = null, isSelected = true)

        // Divider line
        Spacer(
            modifier = Modifier
                .width(40.dp)
                .height(1.dp)
                .background(Color.Gray)
        )

        // Step 3 - Not started
        StepItem(number = "3", text = null, isSelected = false)
    }
}


@Composable
fun StepItem(number: String, text: String?, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val color = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Gray
        val textColor = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Gray
        val backgroundColor = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Transparent

        Row(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .border(1.dp, color, CircleShape),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = number,
                color = if (isSelected) Color.White else textColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (text != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = text, color = White, fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun ProgressIndicatorPreview() {
    ProgressIndicator()
}