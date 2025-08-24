package com.example.recipebox.core.utils.di

import com.example.recipebox.data.repository.CollectionRepositoryImpl
import com.example.recipebox.data.repository.RecipeRepositoryImpl
import com.example.recipebox.domain.repository.CollectionRepository
import com.example.recipebox.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    abstract fun bindCollectionRepository(impl: CollectionRepositoryImpl) : CollectionRepository
}