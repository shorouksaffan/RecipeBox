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
                addRecipeUiState = it.addRecipeUiState.copy(showAddIngredientDialog = true)
            )
        }
    }

    fun onCloseAddIngredientDialog() {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(showAddIngredientDialog = false)
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
                    addRecipeUiState = it.addRecipeUiState.copy(ingredients = currentIngredients, showAddIngredientDialog = false)
                )
            }
        }
    }

    fun onOpenAddInstructionDialog() {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(showAddInstructionDialog = true)
            )
        }
    }

    fun onCloseAddInstructionDialog() {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(showAddInstructionDialog = false)
            )
        }
    }

    fun onAddInstruction(instruction: String) {
        val trimmedInstruction = instruction.trim()
        if (trimmedInstruction.isNotBlank()) {
            val currentInstructions = addRecipeScreenState.value.addRecipeUiState.instructions.toMutableList()
            currentInstructions.add(trimmedInstruction)
            _addRecipeScreenState.update {
                it.copy(
                    addRecipeUiState = it.addRecipeUiState.copy(instructions = currentInstructions, showAddInstructionDialog = false)
                )
            }
        }
    }

    fun onRecipeNameChange(name: String) {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(recipeName = name)
            )
        }
    }

    fun onIncrementServingCount() {
        val currentCount = addRecipeScreenState.value.addRecipeUiState.servingCount
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(servingCount = currentCount + 1)
            )
        }
    }

    fun onDecrementServingCount() {
        val currentCount = addRecipeScreenState.value.addRecipeUiState.servingCount
        if (currentCount > 0) {
            _addRecipeScreenState.update {
                it.copy(
                    addRecipeUiState = it.addRecipeUiState.copy(servingCount = currentCount - 1)
                )
            }
        }
    }

    fun onCookTimeHoursChange(hours: String) {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(cookTimeHours = hours)
            )
        }
    }

    fun onCookTimeMinutesChange(minutes: String) {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(cookTimeMinutes = minutes)
            )
        }
    }

    fun onDifficultySelected(difficulty: Difficulty) {
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(selectedDifficulty = difficulty)
            )
        }
    }

    fun onDishTypeSelected(dishType: DishType) {
        val currentSelections = addRecipeScreenState.value.addRecipeUiState.selectedDishTypes.toMutableSet()
        if (currentSelections.contains(dishType)) {
            currentSelections.remove(dishType)
        } else {
            currentSelections.add(dishType)
        }
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(selectedDishTypes = currentSelections)
            )
        }
    }

    fun onDietaryTargetSelected(dietaryTarget: DietaryTarget) {
        val currentSelections = addRecipeScreenState.value.addRecipeUiState.selectedDietaryTargets.toMutableSet()
        if (currentSelections.contains(dietaryTarget)) {
            currentSelections.remove(dietaryTarget)
        } else {
            currentSelections.add(dietaryTarget)
        }
        _addRecipeScreenState.update {
            it.copy(
                addRecipeUiState = it.addRecipeUiState.copy(selectedDietaryTargets = currentSelections)
            )
        }
    }
}
