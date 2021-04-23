package com.example.recyclerviewsample.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NameDatabaseDao {
    @Insert
    fun insert(sample: NameData)

    @Query("DELETE  FROM name_data ")
    suspend fun clear()

    @Query("SELECT * FROM name_data ORDER BY nameId DESC")
    suspend fun getAllTasks(): List<NameData>
}