package com.example.recipebox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val ScreenBg = Color(0xFFF5FAF0)
private val HeaderYellow = Color(0xFFDEE21B)
private val SectionBlue = Color(0xFF4058A0)
private val LabelRed = Color(0xFFFF7043)
private val ChipSelected = Color(0xFFD6E415)
private val ChipUnselected = Color.Transparent
private val ChipBorder = Color(0xFFE1E1E1)
private val ButtonBlack = Color(0xFF111111)

@Composable
fun Filterchip(
    modifier: Modifier = Modifier,
    onConfirm: (Int, String, List<String>) -> Unit = { _, _, _ -> },
    onClearAll: () -> Unit = {}
) {
    var cookTime by remember { mutableStateOf(48f) }
    var selectedDifficulty by remember { mutableStateOf("Medium") }
    var selectedTypes by remember { mutableStateOf(setOf("Snack", "Brunch")) }

    Scaffold(
        containerColor = ScreenBg,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        cookTime = 0f
                        selectedDifficulty = ""
                        selectedTypes = emptySet()
                        onClearAll()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(28.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 2.dp),
                ) {
                    Text("Clear All", fontSize = 16.sp, color = Color.Black)
                }

                Button(
                    onClick = { onConfirm(cookTime.toInt(), selectedDifficulty, selectedTypes.toList()) },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonBlack)
                ) {
                    Text("Confirm", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    ) { inner ->
        Column(
            modifier = modifier
                .padding(inner)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(HeaderYellow)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter", tint = Color.Black)
                Spacer(Modifier.width(8.dp))
                Text("Filter", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            // COOK TIME
            SectionBox(label = "Cook Time") {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Slider(
                        value = cookTime,
                        onValueChange = { cookTime = it },
                        valueRange = 0f..120f,
                        colors = SliderDefaults.colors(
                            thumbColor = HeaderYellow,
                            activeTrackColor = HeaderYellow,
                            inactiveTrackColor = Color.White
                        )
                    )
                    Text("${cookTime.toInt()} Min", fontSize = 16.sp, color = Color.White)
                }
            }


            SectionBox(label = "Difficulty") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listOf("Easy", "Medium", "Professional").forEach { option ->
                        SelectableChip(
                            text = option,
                            isSelected = selectedDifficulty == option,
                            onClick = { selectedDifficulty = option }
                        )
                    }
                }
            }


            SectionBox(label = "Dish Type") {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        "Breakfast", "Lunch", "Snack", "Brunch",
                        "Dessert", "Dinner", "Appetizers"
                    ).forEach { type ->
                        SelectableChip(
                            text = type,
                            isSelected = type in selectedTypes,
                            onClick = {
                                selectedTypes = if (type in selectedTypes) {
                                    selectedTypes - type
                                } else {
                                    selectedTypes + type
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionBox(label: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 0.dp, bottomEnd = 8.dp, bottomStart = 0.dp))
                .background(LabelRed)
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(label, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SectionBlue)
                .padding(16.dp)
        ) {
            Column(content = content)
        }
    }
}

@Composable
private fun SelectableChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(if (isSelected) ChipSelected else ChipUnselected)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            fontSize = 14.sp,
            color = if (isSelected) Color.Black else Color.White,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF5FAF0)
private fun FilterchipPreview() {
    MaterialTheme {
        Filterchip()
    }
}