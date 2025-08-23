package com.example.recipebox.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipebox.data.local.converters.Converters
import com.example.recipebox.data.local.dao.*
import com.example.recipebox.data.local.entities.*

@Database(
    entities = [
        RecipeEntity::class,
        CollectionEntity::class,
        CollectionRecipeCrossRefEntity::class,
        ProfileEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RecipeBoxDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun collectionDao(): CollectionDao
    abstract fun collectionRecipeCrossRefDao(): CollectionRecipeCrossRefDao
    abstract fun profileDao(): ProfileDao
}