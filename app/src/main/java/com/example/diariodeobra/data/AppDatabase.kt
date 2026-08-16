package com.example.diariodeobra.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diariodeobra.data.dao.DailyReportDao
import com.example.diariodeobra.data.dao.WorkDao
import com.example.diariodeobra.data.model.DailyReportEntity
import com.example.diariodeobra.data.model.MediaEntity
import com.example.diariodeobra.data.model.WorkEntity

@Database(
    entities = [DailyReportEntity::class, MediaEntity::class, WorkEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dailyReportDao(): DailyReportDao
    abstract fun workDao(): WorkDao
}



