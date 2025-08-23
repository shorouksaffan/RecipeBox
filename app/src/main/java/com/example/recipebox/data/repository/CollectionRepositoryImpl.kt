package com.example.recipebox.data.repository

import com.example.recipebox.data.local.dao.CollectionDao
import com.example.recipebox.data.mapper.toDomain
import com.example.recipebox.data.mapper.toEntity
import com.example.recipebox.domain.model.Collection
import com.example.recipebox.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CollectionRepositoryImpl(
    private val collectionDao: CollectionDao
) : CollectionRepository {
    override fun getAllCollections(): Flow<List<Collection>> =
        collectionDao.getAllCollections().map { list -> list.map { it.toDomain() } }

    override suspend fun addCollection(collection: Collection) {
        collectionDao.insert(collection.toEntity())
    }
    override suspend fun updateCollection(collection: Collection) {
        collectionDao.update(collection.toEntity())
    }

    override suspend fun getCollectionById(id: Long): Collection? {
        return collectionDao.getCollectionById(id)?.toDomain()
    }

    override suspend fun deleteCollection(collection: Collection) {
        collectionDao.delete(collection.toEntity())
    }
}