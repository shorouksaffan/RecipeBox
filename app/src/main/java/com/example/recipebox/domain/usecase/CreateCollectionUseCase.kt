package com.example.recipebox.domain.usecase

import com.example.recipebox.domain.model.Collection
import com.example.recipebox.domain.repository.CollectionRepository

class CreateCollectionUseCase(private val repository: CollectionRepository) {
    suspend operator fun invoke(collection: Collection) = repository.addCollection(collection)
}