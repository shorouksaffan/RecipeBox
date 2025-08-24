package com.example.recipebox.domain.usecase


import com.example.recipebox.domain.model.Collection
import com.example.recipebox.domain.repository.CollectionRepository
import javax.inject.Inject

class DeleteCollectionUseCase @Inject constructor(private val repository: CollectionRepository) {
    suspend operator fun invoke(collection: Collection): Unit = repository.deleteCollection(collection)
}