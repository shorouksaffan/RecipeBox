package com.example.recipebox.ui.addrecipe


data class AddRecipeScreenState(
    val isLoading: Boolean = false, //TODO
    val addRecipeUiState: AddRecipeUiState = AddRecipeUiState(),
    val error: String? = null
)

data class AddRecipeUiState(
    val currentStep : AddRecipeStep = AddRecipeStep.AddRecipeCover,
    val message: String? = null,
)

enum class AddRecipeStep {
    AddRecipeCover,
    PreviewRecipeCover,
    AddRecipeInfo,
    AddIngredients,
    AddInstructions,
}