package com.example.recipebox.data.local.dao

import androidx.room.*
import com.example.recipebox.data.local.entities.ProfileEntity

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: ProfileEntity)

    @Query("SELECT * FROM profile WHERE id = 0")
    suspend fun getProfile(): ProfileEntity?

    @Update
    suspend fun update(profile: ProfileEntity)
}