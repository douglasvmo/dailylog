package com.example.diariodeobra.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.diariodeobra.data.model.DailyReportEntity
import com.example.diariodeobra.data.model.DailyReportWithPhotos
import com.example.diariodeobra.data.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyReportDao {
    @Query("SELECT * FROM daily_reports")
    fun observeAll(): Flow<List<DailyReportEntity>>

    @Query("SELECT * FROM daily_reports WHERE workId = :workId")
    fun observeAllByWorkId(workId: String): Flow<List<DailyReportEntity>>

    @Transaction
    @Query("SELECT * FROM daily_reports WHERE workId = :workId")
    fun getAllByWorkIdWithPhotos(workId: String): DailyReportWithPhotos
    
    @Insert
    fun insert(dailyReport: DailyReportEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos: List<PhotoEntity>)



}