package com.example.recipebox.ui.addrecipe.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipebox.ui.addrecipe.AddRecipeUiState
import com.example.recipebox.ui.addrecipe.AddRecipeViewModel
import com.example.recipebox.ui.theme.Black
import com.example.recipebox.ui.theme.White

@Composable
fun RecipeIntroductionPreviewScreen(state: AddRecipeUiState, viewModel: AddRecipeViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0),
        containerColor = MaterialTheme.colorScheme.background,
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
        }
    ) { paddingValues ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
                    .padding(paddingValues),
            ) {
                Text("Add Instructions Screen")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.clickable(onClick = viewModel::onBackClick)
                )
            }
        }
    }
}