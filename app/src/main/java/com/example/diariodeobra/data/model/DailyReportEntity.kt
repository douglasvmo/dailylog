package com.example.diariodeobra.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diariodeobra.domain.model.Climate
import com.example.diariodeobra.domain.model.DailyReport
import com.example.diariodeobra.domain.model.DailyReportStatus
import java.time.LocalDate
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "daily_reports")
data class DailyReportEntity (
    @PrimaryKey
    var id: String,
    var workId: String,
    var date: LocalDate,
    var climate: Climate,
    var title: String,
    var activities: String,
    var occurrences: String?,
    var status: String
) {

    fun toDomain(): DailyReport = DailyReport(
        id = this.id,
        workId = this.workId,
        date = this.date,
        climate = this.climate,
        title = this.title,
        activities = this.activities,
        occurrences = this.occurrences,
        status = DailyReportStatus.valueOf(this.status),
        medias = emptyList()
    )

    companion object {


        @OptIn(ExperimentalUuidApi::class)
        fun fromDomain(dailyReport: DailyReport): DailyReportEntity = DailyReportEntity(
            id = dailyReport.id ?: Uuid.generateV7().toString(),
            workId = dailyReport.workId,
            date = dailyReport.date,
            climate = dailyReport.climate!!,
            title = dailyReport.title,
            activities = dailyReport.activities,
            occurrences = dailyReport.occurrences,
            status = dailyReport.status.name
        )
    }
}

