@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.recipebox.ui.search

import androidx.compose.material3.SliderDefaults
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipebox.ui.components.FilterSection
import com.example.recipebox.ui.components.SelectableChip


@Composable
fun FilterScreen() {
    var cookTime by remember { mutableStateOf(48f) }
    var difficulty by remember { mutableStateOf("Medium") }
    val dishTypes = remember { mutableStateListOf("Snack", "Brunch") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF5FAF5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFD7DF23))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Filter",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Filter", fontWeight = FontWeight.Bold, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cook Time
        FilterSection(title = "Cook Time") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF3B4FA3))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Slider(
                    value = cookTime,
                    onValueChange = { cookTime = it },
                    valueRange = 0f..120f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Transparent,        // hide default thumb
                        activeTrackColor = Color(0xFFD7DF23),  // yellow track
                        inactiveTrackColor = Color.White
                    ),
                    thumb = {
                        // Draw a proper circle dot thumb
                        Canvas(
                            modifier = Modifier.size(20.dp) // thumb size
                        ) {
                            drawCircle(
                                color = Color(0xFFD7DF23),
                                radius = size.minDimension / 2  // ensures perfect circle
                            )
                        }
                    }
                )
                Text("${cookTime.toInt()} Min", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Difficulty
        FilterSection(title = "Difficulty") {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF3B4FA3))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("Easy", "Medium", "Professional").forEach { level ->
                    SelectableChip (
                        text = level,
                        isSelected = (difficulty == level),
                        onClick = { difficulty = level }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Dish Type
        FilterSection(title = "Dish Type") {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF3B4FA3))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val options = listOf("Breakfast", "Lunch", "Snack", "Brunch", "Dessert", "Dinner", "Appetizers")
                options.forEach { type ->
                    SelectableChip(
                        text = type,
                        isSelected = dishTypes.contains(type),
                        onClick = {
                            if (dishTypes.contains(type)) dishTypes.remove(type)
                            else dishTypes.add(type)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Bottom Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = {
                    cookTime = 0f
                    difficulty = "Easy"
                    dishTypes.clear()
                },
                shape = CircleShape,
                modifier = Modifier.width(140.dp)
            ) {
                Text("Clear All", color = Color.Black)
            }

            Button(
                onClick = { /* Confirm logic */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier.width(140.dp)
            ) {
                Text("Confirm", color = Color.White)
            }
        }
    }
}


@Preview
@Composable
fun Test(){
    FilterScreen()
}
