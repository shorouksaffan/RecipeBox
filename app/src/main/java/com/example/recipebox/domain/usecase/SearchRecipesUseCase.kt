package com.example.recipebox.domain.usecase

import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(query: String): Flow<List<Recipe>> = repository.searchRecipes(query)
}