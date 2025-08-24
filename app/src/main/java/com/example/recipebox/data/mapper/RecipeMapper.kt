package com.example.recipebox.data.mapper

import com.example.recipebox.data.local.entities.RecipeEntity
import com.example.recipebox.domain.model.Recipe

fun RecipeEntity.toDomain() = Recipe(
    id = id,
    title = title,
    imageUri = imageUri,
    ingredients = ingredients,
    steps = steps,
    tags = tags
)

fun Recipe.toEntity() = RecipeEntity(
    title = title,
    imageUri = imageUri,
    ingredients = ingredients,
    steps = steps,
    tags = tags
)