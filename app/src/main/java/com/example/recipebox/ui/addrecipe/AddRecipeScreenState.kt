package com.example.recipebox.ui.addrecipe

import android.net.Uri


data class AddRecipeScreenState(
    val isLoading: Boolean = false,
    val addRecipeUiState: AddRecipeUiState = AddRecipeUiState(),
    val error: String? = null
)

data class AddRecipeUiState(
    val currentStep: AddRecipeStep = AddRecipeStep.AddRecipeCover,
    val message: String? = null,
    val ingredients: List<String> = emptyList(),
    val showAddIngredientDialog: Boolean = false,
    val instructions: List<String> = emptyList(),
    val showAddInstructionDialog: Boolean = false,

    val coverImageUri: Uri? = null,
    val recipeName: String = "",
    val servingCount: Int = 4,
    val cookTimeHours: String = "",
    val cookTimeMinutes: String = "",
    val difficulties: List<Difficulty> = Difficulty.entries,
    val selectedDifficulty: Difficulty = Difficulty.Easy,
    val dishTypes: List<DishType> = DishType.entries,
    val selectedDishTypes: Set<DishType> = emptySet(),
    val dietaryTargets: List<DietaryTarget> = DietaryTarget.entries,
    val selectedDietaryTargets: Set<DietaryTarget> = emptySet(),
    val hashtags: String = "#egg #Vegan #Sugerfree #lowfat"
)

enum class Difficulty {
    Easy, Medium, Hard
}

enum class DishType {
    Breakfast,
    Lunch,
    Snack,
    Brunch,
    Dessert,
    Dinner,
    Appetizers
}

enum class DietaryTarget {
    Vegetarian,
    HighFat,
    LowFat,
    SugarFree,
    LactoseFree,
    GlutenFree
}

enum class AddRecipeStep {
    AddRecipeCover,
    PreviewRecipeCover,
    AddRecipeInfo,
    AddIngredients,
    AddInstructions,
    RecipeIntroductionPreview
}