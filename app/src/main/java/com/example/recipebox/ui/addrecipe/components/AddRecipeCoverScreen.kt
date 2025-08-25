package com.example.recipebox.ui.addrecipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipebox.ui.addrecipe.AddRecipeUiState
import com.example.recipebox.ui.addrecipe.AddRecipeViewModel

@Composable
fun AddRecipeCoverScreen(state: AddRecipeUiState, viewModel: AddRecipeViewModel) {
    Scaffold(
        topBar = {
            AddRecipeCoverTobBar(viewModel::onNextClick)
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0),
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Text("Add Recipe Cover Screen")
        }
    }
}