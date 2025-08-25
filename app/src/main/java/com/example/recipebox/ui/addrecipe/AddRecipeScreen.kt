package com.example.recipebox.ui.addrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipebox.ui.addrecipe.components.AddRecipeScreenSuccessContent
import com.example.recipebox.ui.theme.Info

@Composable
fun AddRecipeScreen(addRecipeViewModel: AddRecipeViewModel = hiltViewModel()) {
    val state by addRecipeViewModel.addRecipeScreenState.collectAsStateWithLifecycle()
    AddRecipeContent(
        state = state,
        viewModel = addRecipeViewModel
    )
}

@Composable
private fun AddRecipeContent(
    state: AddRecipeScreenState,
    viewModel: AddRecipeViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.error != null -> {
                Text(text = state.error, color = MaterialTheme.colorScheme.error)
            }

            state.isLoading -> {
                CircularProgressIndicator(color = Info)
            }

            else -> {
                AddRecipeScreenSuccessContent(
                    state = state.addRecipeUiState,
                    viewModel = viewModel
                )
            }
        }
    }
}

