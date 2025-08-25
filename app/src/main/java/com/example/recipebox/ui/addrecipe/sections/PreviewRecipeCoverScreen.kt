package com.example.recipebox.ui.addrecipe.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.recipebox.ui.addrecipe.AddRecipeUiState
import com.example.recipebox.ui.addrecipe.AddRecipeViewModel
import com.example.recipebox.ui.addrecipe.components.NewRecipeTopBar
import com.example.recipebox.ui.components.progressInd_step1
import com.example.recipebox.ui.theme.Black
import com.example.recipebox.ui.theme.RecipeBoxTypography
import com.example.recipebox.ui.theme.White

@Composable
fun PreviewRecipeCoverScreen(
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
                Text("Save", color = White)
            }
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0),
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            progressInd_step1()

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Preview", style = RecipeBoxTypography.h6, color = Black)
            state.coverImageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .padding(horizontal = 16.dp, vertical = 36.dp)
                )
            }
            OutlinedButton(
                onClick = viewModel::onRemoveImageClick,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                Text("Remove", color = Black)
            }
        }
    }
}