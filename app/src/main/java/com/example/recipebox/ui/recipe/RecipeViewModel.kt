package com.example.recipebox.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.usecase.GetRecipeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RecipeState {
    object Loading : RecipeState()
    data class Success(val recipe: Recipe) : RecipeState()
    data class Error(val error: String) : RecipeState()
}

@HiltViewModel
class RecipeViewModel @Inject constructor(private val getRecipeByIdUseCase: GetRecipeByIdUseCase) : ViewModel() {
    private val _recipeState = MutableStateFlow<RecipeState>(RecipeState.Loading)
    val recipeState: StateFlow<RecipeState> = _recipeState

    fun getRecipeById(recipeId: Long) {
        viewModelScope.launch {
            _recipeState.value = RecipeState.Loading
            try {
                val recipe = getRecipeByIdUseCase(recipeId)
                _recipeState.value = RecipeState.Success(recipe)
            } catch (e: Exception) {
                _recipeState.value = RecipeState.Error("Failed to load recipe: ${e.message}")
            }
        }
    }
}