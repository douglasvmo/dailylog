package com.example.diariodeobra.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.diariodeobra.data.model.WorkEntity

@Dao
interface WorkDao {
    @Query("SELECT * FROM works")
    fun getAll(): List<WorkEntity>

    @Insert
    fun insert(work: WorkEntity)
}