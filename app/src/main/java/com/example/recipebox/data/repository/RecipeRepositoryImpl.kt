package com.example.recipebox.data.repository

import com.example.recipebox.data.local.dao.RecipeDao
import com.example.recipebox.data.mapper.toDomain
import com.example.recipebox.data.mapper.toEntity
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override fun getAllRecipes(): Flow<List<Recipe>> =
        recipeDao.getAllRecipes().map { list -> list.map { it.toDomain() } }

    override suspend fun addRecipe(recipe: Recipe) {
        recipeDao.insert(recipe.toEntity())
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.delete(recipe.toEntity())
    }
    override suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.update(recipe.toEntity())
    }

    override suspend fun getRecipeById(id: Long): Recipe? {
        return recipeDao.getRecipeById(id)?.toDomain()
    }

    override fun searchRecipes(query: String): Flow<List<Recipe>> =
        recipeDao.searchRecipesByTitle(query).map { list -> list.map { it.toDomain() } }
}