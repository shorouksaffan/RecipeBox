package com.example.recipebox.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var INSTANCE: RecipeBoxDatabase? = null
        fun getInstance(context: Context): RecipeBoxDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RecipeBoxDatabase::class.java,
                    "recipe_box_database"
                ).fallbackToDestructiveMigration(false).build().also { INSTANCE = it }
            }
        }
    }
}