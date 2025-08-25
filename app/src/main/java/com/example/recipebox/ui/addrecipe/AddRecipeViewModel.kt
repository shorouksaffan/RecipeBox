package com.example.recipebox.ui.addrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.usecase.AddRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(private val addRecipeUseCase: AddRecipeUseCase) :
    ViewModel() {
    private val _addRecipeScreenState = MutableStateFlow(AddRecipeScreenState())
    val addRecipeScreenState: StateFlow<AddRecipeScreenState> = _addRecipeScreenState

    private fun updateState(newState: AddRecipeScreenState) {
        _addRecipeScreenState.update { newState }
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            updateState(
                addRecipeScreenState.value.copy(
                    isLoading = true,
                    error = null,
                    addRecipeUiState = addRecipeScreenState.value.addRecipeUiState.copy(message = null)
                )
            )
            try {
                addRecipeUseCase(recipe)
                updateState(
                    addRecipeScreenState.value.copy(
                        isLoading = false,
                        error = null,
                        addRecipeUiState = addRecipeScreenState.value.addRecipeUiState.copy(
                            message = "Recipe added successfully"
                        )
                    )
                )
            } catch (e: Exception) {
                updateState(
                    addRecipeScreenState.value.copy(
                        isLoading = false,
                        error = "Failed to add recipe: ${e.message}"
                    )
                )
            }
        }
    }

    fun onNextClick() {
        val nextStep = when (addRecipeScreenState.value.addRecipeUiState.currentStep) {
            AddRecipeStep.AddRecipeCover -> AddRecipeStep.PreviewRecipeCover
            AddRecipeStep.PreviewRecipeCover -> AddRecipeStep.AddRecipeInfo
            AddRecipeStep.AddRecipeInfo -> AddRecipeStep.AddIngredients
            AddRecipeStep.AddIngredients -> AddRecipeStep.AddInstructions
            AddRecipeStep.AddInstructions -> AddRecipeStep.RecipeIntroductionPreview
            AddRecipeStep.RecipeIntroductionPreview -> AddRecipeStep.RecipeIntroductionPreview
        }
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(currentStep = nextStep)
            )
        }
    }

    fun onBackClick() {
        val previousStep = when (addRecipeScreenState.value.addRecipeUiState.currentStep) {
            AddRecipeStep.AddRecipeCover -> AddRecipeStep.AddRecipeCover
            AddRecipeStep.PreviewRecipeCover -> AddRecipeStep.AddRecipeCover
            AddRecipeStep.AddRecipeInfo -> AddRecipeStep.PreviewRecipeCover
            AddRecipeStep.AddIngredients -> AddRecipeStep.AddRecipeInfo
            AddRecipeStep.AddInstructions -> AddRecipeStep.AddIngredients
            AddRecipeStep.RecipeIntroductionPreview -> AddRecipeStep.AddInstructions
        }
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(currentStep = previousStep)
            )
        }
    }

    fun onClearAllClick () {
        // TODO: Implement clear all functionality
    }

    fun onSaveImageClick () {
        //TODO: Implement save image functionality
        onNextClick()
    }

    fun onOpenAddIngredientDialog() {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(showAddDialog = true)
            )
        }
    }

    fun onCloseAddIngredientDialog() {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(showAddDialog = false)
            )
        }
    }

    fun onAddIngredient(ingredient: String) {
        val trimmedIngredient = ingredient.trim()
        if (trimmedIngredient.isNotBlank()) {
            val currentIngredients = addRecipeScreenState.value.addRecipeUiState.ingredients.toMutableList()
            currentIngredients.add(trimmedIngredient)
            _addRecipeScreenState.update {
                it.copy(
                    addRecipeUiState = it.addRecipeUiState.copy(ingredients = currentIngredients, showAddDialog = false)
                )
            }
        }
    }
}
