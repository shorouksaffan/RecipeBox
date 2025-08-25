package com.example.recipebox.ui.addrecipe.components

import androidx.compose.runtime.Composable
import com.example.recipebox.ui.addrecipe.AddRecipeStep
import com.example.recipebox.ui.addrecipe.AddRecipeUiState
import com.example.recipebox.ui.addrecipe.AddRecipeViewModel
import com.example.recipebox.ui.addrecipe.sections.AddIngredientsScreen
import com.example.recipebox.ui.addrecipe.sections.AddInstructionsScreen
import com.example.recipebox.ui.addrecipe.sections.AddRecipeCoverScreen
import com.example.recipebox.ui.addrecipe.sections.AddRecipeInfoScreen
import com.example.recipebox.ui.addrecipe.sections.PreviewRecipeCoverScreen
import com.example.recipebox.ui.addrecipe.sections.RecipeIntroductionPreviewScreen

@Composable
fun AddRecipeScreenSuccessContent(state: AddRecipeUiState, viewModel: AddRecipeViewModel) {
    when (state.currentStep) {
        AddRecipeStep.AddRecipeCover -> AddRecipeCoverScreen(state, viewModel)
        AddRecipeStep.PreviewRecipeCover -> PreviewRecipeCoverScreen(state, viewModel)
        AddRecipeStep.AddRecipeInfo -> AddRecipeInfoScreen(state, viewModel)
        AddRecipeStep.AddIngredients -> AddIngredientsScreen(state, viewModel)
        AddRecipeStep.AddInstructions -> AddInstructionsScreen(state, viewModel)
        AddRecipeStep.RecipeIntroductionPreview -> RecipeIntroductionPreviewScreen(state, viewModel)
    }
}