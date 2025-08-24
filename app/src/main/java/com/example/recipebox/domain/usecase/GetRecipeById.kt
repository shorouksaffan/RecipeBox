package com.example.recipebox.domain.usecase

import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeById @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(id: Long): Recipe? = repository.getRecipeById(id)
}