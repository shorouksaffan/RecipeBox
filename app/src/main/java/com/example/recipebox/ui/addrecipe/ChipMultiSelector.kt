package com.example.recipebox.ui.addrecipe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.recipebox.ui.theme.Black
import com.example.recipebox.ui.theme.White

@Composable
fun <T : Enum<T>> ChipMultiSelector(
    options: List<T>,
    selectedOptions: Set<T>,
    onOptionSelected: (T) -> Unit,
    getName: (T) -> String = { it.name }
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(options) { option ->
            val isSelected = selectedOptions.contains(option)
            Button(
                onClick = { onOptionSelected(option) },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Transparent,
                    contentColor = if (isSelected) Black else White
                ),
                border = BorderStroke(
                    1.dp,
                    if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Gray
                )
            ) {
                Text(
                    text = getName(option),
                    fontSize = 12.sp,
                    color = if (isSelected) Black else White
                )
            }
        }
    }
}