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
    val medias: List<MediaEntity>
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
        medias = this.medias.map { it.toDomain() }
    )

    companion object {
        fun fromDomain(dailyReport: DailyReport): DailyReportWithPhotos {
            val dailyReportEntity = DailyReportEntity.fromDomain(dailyReport)
            val mediasEntities = dailyReport.medias.map { MediaEntity.fromDomain(it, dailyReportEntity.id) }
            return DailyReportWithPhotos(dailyReportEntity, mediasEntities)
        }

    }

}