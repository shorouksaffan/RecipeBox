package com.example.recipebox.domain.model

data class Recipe(
    val id: Long = 0,
    val title: String,
    val imageUri: String?,
    val ingredients: List<String>,
    val steps: List<String>,
    val tags: List<String>
)