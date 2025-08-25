package com.example.recipebox.ui.addrecipe.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebox.ui.addrecipe.AddRecipeUiState
import com.example.recipebox.ui.addrecipe.AddRecipeViewModel
import com.example.recipebox.ui.addrecipe.ChipMultiSelector
import com.example.recipebox.ui.addrecipe.components.ChipSelector
import com.example.recipebox.ui.addrecipe.components.FormSection
import com.example.recipebox.ui.addrecipe.components.ServingCounter
import com.example.recipebox.ui.addrecipe.components.TimeInputField
import com.example.recipebox.ui.addrecipe.components.NewRecipeTopBar
import com.example.recipebox.ui.components.progressInd_step1
import com.example.recipebox.ui.theme.Black
import com.example.recipebox.ui.theme.White

@Composable
fun AddRecipeInfoScreen(
    state: AddRecipeUiState,
    viewModel: AddRecipeViewModel
) {
    Scaffold(
        topBar = {
            NewRecipeTopBar(
                onClickBack = viewModel::onBackClick,
                onClickClearAll = viewModel::onClearAllClick
            )
        },
        bottomBar = {
            Button(
                onClick = viewModel::onNextClick,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Black),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text("Next", color = White)
            }
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0),
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            progressInd_step1()

            Spacer(modifier = Modifier.height(24.dp))


            Column(
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FormSection(
                    title = "Name",
                    content = {
                        TextField(
                            value = state.recipeName,
                            onValueChange = viewModel::onRecipeNameChange,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Name your new recipe", color = Color.Gray) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                errorContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Gray,
                                unfocusedIndicatorColor = Color.LightGray,
                                cursorColor = White,
                                focusedTextColor = White
                            )
                        )
                    }
                )

                FormSection(
                    title = "Number",
                    content = {
                        ServingCounter(
                            count = state.servingCount,
                            onIncrement = viewModel::onIncrementServingCount,
                            onDecrement = viewModel::onDecrementServingCount
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
                                value = state.cookTimeHours,
                                onValueChange = viewModel::onCookTimeHoursChange,
                                placeholder = "h",
                                modifier = Modifier.weight(1f)
                            )
                            TimeInputField(
                                value = state.cookTimeMinutes,
                                onValueChange = viewModel::onCookTimeMinutesChange,
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
                            options = state.difficulties,
                            selectedOption = state.selectedDifficulty,
                            onOptionSelected = viewModel::onDifficultySelected
                        )
                    }
                )

                FormSection(
                    title = "Dish Type",
                    content = {
                        ChipMultiSelector(
                            options = state.dishTypes,
                            selectedOptions = state.selectedDishTypes,
                            onOptionSelected = viewModel::onDishTypeSelected
                        )
                    }
                )

                FormSection(
                    title = "Suggested Dietary Target",
                    content = {
                        ChipMultiSelector(
                            options = state.dietaryTargets,
                            selectedOptions = state.selectedDietaryTargets,
                            onOptionSelected = viewModel::onDietaryTargetSelected
                        )
                    }
                )

                FormSection(
                    title = "Hashtags",
                    content = {
                        Text(text = state.hashtags, color = White, fontSize = 14.sp)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}