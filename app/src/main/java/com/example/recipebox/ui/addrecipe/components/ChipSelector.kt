package com.example.recipebox.ui.addrecipe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebox.ui.addrecipe.Difficulty
import com.example.recipebox.ui.theme.Black
import com.example.recipebox.ui.theme.White

@Composable
fun <T : Enum<T>> ChipSelector(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption
            Button(
                onClick = { onOptionSelected(option) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Transparent,
                    contentColor = if (isSelected) Black else White
                ),
                shape = RoundedCornerShape(50),
                border = BorderStroke(
                    1.dp,
                    if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Gray
                )
            ) {
                Text(
                    text = option.name,
                    fontSize = 12.sp,
                    color = if (isSelected) Black else White
                )
            }
        }
    }
}