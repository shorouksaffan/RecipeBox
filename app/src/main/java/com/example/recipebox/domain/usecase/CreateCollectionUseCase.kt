package com.example.recipebox.domain.usecase

import com.example.recipebox.domain.model.Collection
import com.example.recipebox.domain.repository.CollectionRepository
import javax.inject.Inject

class CreateCollectionUseCase @Inject constructor(private val repository: CollectionRepository) {
    suspend operator fun invoke(collection: Collection) = repository.addCollection(collection)
}