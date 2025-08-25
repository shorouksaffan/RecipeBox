package com.example.recipebox.ui.addrecipe


data class AddRecipeScreenState(
    val isLoading: Boolean = false,
    val addRecipeUiState: AddRecipeUiState = AddRecipeUiState(),
    val error: String? = null
)

data class AddRecipeUiState(
    val currentStep : AddRecipeStep = AddRecipeStep.AddRecipeCover,
    val message: String? = null,
    val ingredients: List<String> = emptyList(),
    val showAddDialog : Boolean = false,
)

enum class AddRecipeStep {
    AddRecipeCover,
    PreviewRecipeCover,
    AddRecipeInfo,
    AddIngredients,
    AddInstructions,
    RecipeIntroductionPreview
}