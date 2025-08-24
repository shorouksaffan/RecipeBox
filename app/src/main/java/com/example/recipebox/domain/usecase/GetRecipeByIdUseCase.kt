package com.example.recipebox.domain.usecase

import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(id: Long): Recipe? = repository.getRecipeById(id)
}