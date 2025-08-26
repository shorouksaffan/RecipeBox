package com.example.recipebox.core.utils.di

import android.content.Context
import com.example.recipebox.data.local.RecipeBoxDatabase
import com.example.recipebox.data.local.dao.CollectionDao
import com.example.recipebox.data.local.dao.CollectionRecipeCrossRefDao
import com.example.recipebox.data.local.dao.ProfileDao
import com.example.recipebox.data.local.dao.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): RecipeBoxDatabase {
        return RecipeBoxDatabase.getInstance(context)
    }

    @Provides
    fun provideRecipeDao(database: RecipeBoxDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    fun provideCollectionDao(database: RecipeBoxDatabase): CollectionDao {
        return database.collectionDao()
    }

    @Provides
    fun provideCollectionRecipeCrossRefDao(database: RecipeBoxDatabase): CollectionRecipeCrossRefDao {
        return database.collectionRecipeCrossRefDao()
    }

    @Provides
    fun provideProfileDao(database: RecipeBoxDatabase): ProfileDao {
        return database.profileDao()
    }
}