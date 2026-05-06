package com.example.diariodeobra.data.repository

import com.example.diariodeobra.data.dao.DailyReportDao
import com.example.diariodeobra.data.model.DailyReportEntity
import com.example.diariodeobra.data.model.PhotoEntity
import com.example.diariodeobra.domain.model.DailyReport
import com.example.diariodeobra.domain.repository.DailyReportRepository
import kotlinx.coroutines.flow.map

class DailyReportRoomRepository(
    private val dao: DailyReportDao
) : DailyReportRepository {
    override fun observeReports() = dao.observeAll().map { list -> list.map { it.toDomain() } }

    override suspend fun saveReport(report: DailyReport) {
        dao.insert(DailyReportEntity.fromDomain(report))
        dao.insertPhotos(report.photos.map { PhotoEntity.fromDomain(it).apply { reportId = report.id } })
    }
}

