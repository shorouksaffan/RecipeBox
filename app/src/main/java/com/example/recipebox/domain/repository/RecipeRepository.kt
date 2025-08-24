package com.example.recipebox.domain.repository

import com.example.recipebox.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getAllRecipes(): Flow<List<Recipe>>
    fun searchRecipes(query: String): Flow<List<Recipe>>
    suspend fun addRecipe(recipe: Recipe)
    suspend fun updateRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun getFirstRecipeImageInCollection(collectionId: Long): String?
    suspend fun getRecipeById(id: Long): Recipe?
}