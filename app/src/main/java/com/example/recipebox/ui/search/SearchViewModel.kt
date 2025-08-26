package com.example.recipebox.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.usecase.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SearchState {
    object Loading : SearchState()
    data class Success(val results: List<Recipe>) : SearchState()
    data class Error(val message: String) : SearchState()
}

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRecipesUseCase: SearchRecipesUseCase) :
    ViewModel() {
    private val _searchState = MutableStateFlow<SearchState>(SearchState.Loading)
    val searchState: StateFlow<SearchState> = _searchState

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            try {
                searchRecipesUseCase(query)
                    .collect { recipes ->
                        _searchState.value = SearchState.Success(recipes)
                    }
            } catch (e: Exception) {
                _searchState.value = SearchState.Error("Search failed: ${e.message}")
            }
        }
    }
}