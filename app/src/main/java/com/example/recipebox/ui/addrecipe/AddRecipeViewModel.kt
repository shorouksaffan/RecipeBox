package com.example.recipebox.ui.addrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.usecase.AddRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AddRecipeState {
    object Loading : AddRecipeState()
    data class Success(val message: String) : AddRecipeState()
    data class Error(val error: String) : AddRecipeState()
}

@HiltViewModel
class AddRecipeViewModel @Inject constructor(private val addRecipeUseCase: AddRecipeUseCase) : ViewModel() {
    private val _addRecipeState = MutableStateFlow<AddRecipeState>(AddRecipeState.Loading)
    val addRecipeState: StateFlow<AddRecipeState> = _addRecipeState

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            _addRecipeState.value = AddRecipeState.Loading
            try {
                addRecipeUseCase(recipe)
                _addRecipeState.value = AddRecipeState.Success("Recipe added successfully")
            } catch (e: Exception) {
                _addRecipeState.value = AddRecipeState.Error("Failed to add recipe: ${e.message}")
            }
        }
    }
}
