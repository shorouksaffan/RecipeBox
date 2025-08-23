package com.example.recipebox.core.utils.di

import com.example.recipebox.domain.repository.CollectionRepository
import com.example.recipebox.domain.repository.RecipeRepository
import com.example.recipebox.domain.usecase.AddRecipeUseCase
import com.example.recipebox.domain.usecase.CreateCollectionUseCase
import com.example.recipebox.domain.usecase.GetCollectionUseCase
import com.example.recipebox.domain.usecase.GetRecipesUseCase
import com.example.recipebox.domain.usecase.SearchRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideAddRecipeUseCase(
        repository: RecipeRepository
    ): AddRecipeUseCase {
        return AddRecipeUseCase(repository)
    }

    @Provides
    fun provideGetRecipesUseCase(
        repository: RecipeRepository
    ) : GetRecipesUseCase {
        return GetRecipesUseCase(repository)
    }

    @Provides
    fun provideSearchRecipesUseCase(
        repository: RecipeRepository
    ) : SearchRecipesUseCase {
        return SearchRecipesUseCase(repository)
    }

    @Provides
    fun provideCreateCollectionUseCase(
        repository: CollectionRepository
    ): CreateCollectionUseCase {
        return CreateCollectionUseCase(repository)
    }

    @Provides
    fun provideGetCollectionsUseCase(
        repository: CollectionRepository
    ): GetCollectionUseCase {
        return GetCollectionUseCase(repository)
    }
}