package com.example.recipebox.data.local.dao

import androidx.room.*
import com.example.recipebox.data.local.entities.CollectionRecipeCrossRefEntity

@Dao
interface CollectionRecipeCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ref: CollectionRecipeCrossRefEntity)

    @Delete
    suspend fun delete(ref: CollectionRecipeCrossRefEntity)
}