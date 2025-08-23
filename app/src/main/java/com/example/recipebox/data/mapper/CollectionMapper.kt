package com.example.recipebox.data.mapper

import com.example.recipebox.data.local.entities.CollectionEntity
import com.example.recipebox.domain.model.Collection

fun CollectionEntity.toDomain() = Collection(
    id = id,
    name = name
)

fun Collection.toEntity() = CollectionEntity(
    id = id,
    name = name
)