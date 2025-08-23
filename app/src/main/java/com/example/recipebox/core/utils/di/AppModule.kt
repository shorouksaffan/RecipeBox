package com.example.recipebox.core.utils.di

import android.content.Context
import com.example.recipebox.data.local.RecipeBoxDatabase
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
}