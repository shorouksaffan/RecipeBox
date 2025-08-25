package com.example.recipebox.ui.addrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.recipebox.R
import com.example.recipebox.ui.components.progressInd_step1

private val BackgroundColor = Color.White
private val DarkBlueFormSectionColor = Color(0xFF4058A0)
private val LightBlueTopAppBarColor = Color(0xFF4058A0)
private val OrangeHeaderColor = Color(0xFFFF9800)
private val YellowHighlightColor = Color(0xFFe9c46a)
private val TextColor = Color(0xFFFFFFFF)
private val blackColor = Color(0xFF000000)
val AQRADA = FontFamily(
    Font(R.font.aqrada, FontWeight.Normal)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRecipeStep2Screen() {
    // State variables to manage user input
    var recipeName by remember { mutableStateOf("") }
    var servingCount by remember { mutableStateOf(4) }
    var cookTimeHours by remember { mutableStateOf("") }
    var cookTimeMinutes by remember { mutableStateOf("") }
    var selectedDifficulty by remember { mutableStateOf("Easy") }
    var selectedDishTypes by remember { mutableStateOf(setOf<String>()) }
    var selectedDietaryTargets by remember { mutableStateOf(setOf<String>()) }
    var hashtags by remember { mutableStateOf("#egg #Vegan #Sugerfree #lowfat") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Recipe",
                        color = TextColor,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AQRADA,
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back button */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextColor
                        )
                    }
                },
                actions = {
                    Button(
                        onClick = { /* Handle clear all */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = TextColor
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Clear all", fontSize = 14.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightBlueTopAppBarColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))


            progressInd_step1()

            Spacer(modifier = Modifier.height(24.dp))


            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FormSection(
                    title = "Name",
                    content = {
                        TextField(
                            value = recipeName,
                            onValueChange = { recipeName = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Name your new recipe", color = Color.Gray) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                errorContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Gray,
                                unfocusedIndicatorColor = Color.LightGray,
                                cursorColor = TextColor,
                                focusedTextColor = TextColor
                            )
                        )
                    }
                )

                FormSection(
                    title = "Number",
                    content = {
                        ServingCounter(
                            count = servingCount,
                            onIncrement = { servingCount++ },
                            onDecrement = { if (servingCount > 0) servingCount-- }
                        )
                    }
                )

                FormSection(
                    title = "Cook Time",
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TimeInputField(
                                value = cookTimeHours,
                                onValueChange = { cookTimeHours = it },
                                placeholder = "h",
                                modifier = Modifier.weight(1f)
                            )
                            TimeInputField(
                                value = cookTimeMinutes,
                                onValueChange = { cookTimeMinutes = it },
                                placeholder = "m",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                )

                FormSection(
                    title = "Difficulty",
                    content = {
                        ChipSelector(
                            options = listOf("Easy", "Medium", "Hard"),
                            selectedOption = selectedDifficulty,
                            onOptionSelected = { selectedDifficulty = it }
                        )
                    }
                )

                FormSection(
                    title = "Dish Type",
                    content = {
                        ChipMultiSelector(
                            options = listOf("Breakfast", "Lunch", "Snack", "Brunch", "Dessert", "Dinner", "Appetizers"),
                            selectedOptions = selectedDishTypes,
                            onOptionSelected = {
                                selectedDishTypes = if (selectedDishTypes.contains(it)) {
                                    selectedDishTypes - it
                                } else {
                                    selectedDishTypes + it
                                }
                            }
                        )
                    }
                )

                FormSection(
                    title = "Suggested Dietary Target",
                    content = {
                        ChipMultiSelector(
                            options = listOf("Vegetarian", "High Fat", "Low Fat", "Sugar Free", "Lactose Free", "Gluten Free"),
                            selectedOptions = selectedDietaryTargets,
                            onOptionSelected = {
                                selectedDietaryTargets = if (selectedDietaryTargets.contains(it)) {
                                    selectedDietaryTargets - it
                                } else {
                                    selectedDietaryTargets + it
                                }
                            }
                        )
                    }
                )

                FormSection(
                    title = "Hashtags",
                    content = {
                        Text(text = hashtags, color = TextColor, fontSize = 14.sp)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = { /* Handle next button click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = blackColor)
            ) {
                Text(text = "Next", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}


@Composable
fun FormSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(DarkBlueFormSectionColor)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            color = OrangeHeaderColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}


@Composable
fun ServingCounter(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Serving for", color = TextColor, fontSize = 14.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onDecrement,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(OrangeHeaderColor)
            ) {
                Icon(
                    imageVector = Icons.Filled.Remove,
                    contentDescription = "Decrement",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$count",
                color = TextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = onIncrement,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(OrangeHeaderColor)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Increment",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "People", color = TextColor, fontSize = 14.sp)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        placeholder = { Text(placeholder, color = Color.Gray, textAlign = TextAlign.Center) },
        textStyle = TextStyle(
            color = TextColor,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = TextColor,
            focusedTextColor = TextColor
        ),
        singleLine = true
    )
}


@Composable
fun ChipSelector(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption
            Button(
                onClick = { onOptionSelected(option) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) YellowHighlightColor else Color.Transparent,
                    contentColor = if (isSelected) Color.White else TextColor
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier.border(1.dp, if (isSelected) YellowHighlightColor else Color.Gray, RoundedCornerShape(50))
            ) {
                Text(
                    text = option,
                    fontSize = 12.sp,
                    color = if (isSelected) Color.White else TextColor
                )
            }
        }
    }
}


@Composable
fun ChipMultiSelector(
    options: List<String>,
    selectedOptions: Set<String>,
    onOptionSelected: (String) -> Unit
) {
    // Using a LazyRow for potentially long lists to be more efficient
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(options) { option ->
            val isSelected = selectedOptions.contains(option)
            Button(
                onClick = { onOptionSelected(option) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) YellowHighlightColor else Color.Transparent,
                    contentColor = if (isSelected) Color.White else TextColor
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier.border(1.dp, if (isSelected) YellowHighlightColor else Color.Gray, RoundedCornerShape(50))
            ) {
                Text(
                    text = option,
                    fontSize = 12.sp,
                    color = if (isSelected) Color.White else TextColor
                )
            }
        }
    }
}


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
        val color = if (isSelected) YellowHighlightColor else Color.Gray
        val textColor = if (isSelected) YellowHighlightColor else Color.Gray
        val backgroundColor = if (isSelected) YellowHighlightColor else Color.Transparent

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
            Text(text = text, color = TextColor, fontSize = 12.sp)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewRecipeStep2Screen() {
    NewRecipeStep2Screen()
}