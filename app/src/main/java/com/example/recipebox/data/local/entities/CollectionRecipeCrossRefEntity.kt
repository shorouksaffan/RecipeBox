package com.example.recipebox.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["collectionId", "recipeId"])
data class CollectionRecipeCrossRefEntity(
    val collectionId: Long,
    val recipeId: Long
)