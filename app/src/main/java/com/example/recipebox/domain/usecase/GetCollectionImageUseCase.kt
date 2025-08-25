package com.example.recipebox.domain.usecase

import com.example.recipebox.domain.repository.RecipeRepository

class GetCollectionImageUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(collectionId: Long): String? {
        return repository.getFirstRecipeImageInCollection(collectionId)
    }
}