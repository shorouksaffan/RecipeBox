package com.example.recipebox.domain.usecase

import com.example.recipebox.domain.model.Collection
import com.example.recipebox.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCollectionUseCase @Inject constructor(private val repository: CollectionRepository) {
    operator fun invoke(): Flow<List<Collection>> = repository.getAllCollections()
}