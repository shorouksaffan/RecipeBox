package com.example.recipebox.ui.addrecipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.recipebox.ui.addrecipe.AddRecipeUiState
import com.example.recipebox.ui.addrecipe.AddRecipeViewModel
import com.example.recipebox.ui.components.progressInd_step2
import com.example.recipebox.ui.theme.Black
import com.example.recipebox.ui.theme.DarkGray
import com.example.recipebox.ui.theme.White

@Composable
fun AddIngredientsScreen(
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
                modifier = Modifier.padding(12.dp).fillMaxWidth()
            ) {
                Text("Next", color = White)
            }
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0),
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        if (state.showAddDialog) {
            AddIngredientDialog(
                onDismiss = viewModel::onCloseAddIngredientDialog,
                onConfirm = viewModel::onAddIngredient
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Stepper
            progressInd_step2()

            Spacer(Modifier.height(16.dp))

            // List (starts empty)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(state.ingredients, key = { _, item -> item }) { index, item ->
                    IngredientRow(number = index + 1, text = item)
                }

                item {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(DarkGray)
                            .clickable { viewModel.onOpenAddIngredientDialog() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add ingredient")
                    }
                }

                // Extra bottom spacing so the last item isn't hidden by the bottom bar
                item { Spacer(Modifier.height(64.dp)) }
            }
        }
    }
}