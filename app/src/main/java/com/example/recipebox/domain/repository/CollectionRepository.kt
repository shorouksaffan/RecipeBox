package com.example.recipebox.domain.repository

import com.example.recipebox.domain.model.Collection
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getAllCollections(): Flow<List<Collection>>
    suspend fun addCollection(collection: Collection)
    suspend fun updateCollection(collection: Collection)
    suspend fun deleteCollection(collection: Collection)
    suspend fun getCollectionById(id: Long): Collection?
}