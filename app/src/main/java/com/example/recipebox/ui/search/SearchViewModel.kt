package com.example.recipebox.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.usecase.GetRecipesUseCase
import com.example.recipebox.domain.usecase.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    init {
        // Load all recipes initially
        viewModelScope.launch {
            getRecipesUseCase().collect { list ->
                _recipes.value = list
            }
        }
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery

        viewModelScope.launch {
            if (newQuery.isBlank()) {
                getRecipesUseCase().collect { list ->
                    _recipes.value = list
                }
            } else {
                searchRecipesUseCase(newQuery).collect { list ->
                    _recipes.value = list
                }
            }
        }
    }
}