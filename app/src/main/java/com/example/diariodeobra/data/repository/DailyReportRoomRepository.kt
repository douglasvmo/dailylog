package com.example.diariodeobra.data.repository

import com.example.diariodeobra.data.dao.DailyReportDao
import com.example.diariodeobra.data.model.DailyReportEntity
import com.example.diariodeobra.data.model.MediaEntity
import com.example.diariodeobra.domain.model.DailyReport
import com.example.diariodeobra.domain.repository.DailyReportRepository
import kotlinx.coroutines.flow.map

class DailyReportRoomRepository(
    private val dao: DailyReportDao
) : DailyReportRepository {
    override fun observeReports() = dao.observeAll().map { list -> list.map { it.toDomain() } }

    override suspend fun saveReport(report: DailyReport) {
        val reportEntity = DailyReportEntity.fromDomain(report)
        dao.insert(reportEntity)
        dao.deletePhotosByReportId(reportEntity.id)
        dao.insertPhotos(report.medias.map { MediaEntity.fromDomain(it, reportId = reportEntity.id) })
    }

    override suspend fun getReport(id: String): DailyReport? {
        return dao.getReport(id)?.toDomain()
    }
}

