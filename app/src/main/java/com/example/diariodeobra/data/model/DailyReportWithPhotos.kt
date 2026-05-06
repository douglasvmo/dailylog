package com.example.diariodeobra.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.diariodeobra.domain.model.DailyReport
import com.example.diariodeobra.domain.model.DailyReportStatus

data class DailyReportWithPhotos(
    @Embedded
    val dailyReport: DailyReportEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "reportId"
    )
    val photos: List<PhotoEntity>
) {
    fun toDomain(): DailyReport = DailyReport(
        id = this.dailyReport.id,
        workId = this.dailyReport.workId,
        date = this.dailyReport.date,
        climate = this.dailyReport.climate,
        title = this.dailyReport.title,
        activities = this.dailyReport.activities,
        occurrences = this.dailyReport.occurrences,
        status = DailyReportStatus.valueOf(this.dailyReport.status),
        photos = this.photos.map { it.toDomain() }
    )

    companion object {
        fun fromDomain(dailyReport: DailyReport): DailyReportWithPhotos = DailyReportWithPhotos(
            dailyReport = DailyReportEntity.fromDomain(dailyReport),
            photos = dailyReport.photos.map { PhotoEntity.fromDomain(it).apply { reportId = dailyReport.id } }
        )
    }

}