package com.example.recipebox.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val imageUri: String?,
    val ingredients: List<String>,
    val steps: List<String>,
    val tags: List<String>
)