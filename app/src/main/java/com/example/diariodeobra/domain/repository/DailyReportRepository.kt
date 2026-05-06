package com.example.diariodeobra.domain.repository

import com.example.diariodeobra.domain.model.DailyReport
import kotlinx.coroutines.flow.Flow

interface DailyReportRepository {
    fun  observeReports(): Flow<List<DailyReport>>
    suspend fun saveReport(report: DailyReport)
}