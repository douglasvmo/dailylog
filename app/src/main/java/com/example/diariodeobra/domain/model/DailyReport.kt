package com.example.diariodeobra.domain.model

import java.time.LocalDate

data class DailyReport(
    val id: String?,
    var workId: String,
    var date: LocalDate,
    var climate: Climate?,
    var title: String,
    var activities: String,
    var occurrences: String?,
    var medias: List<MediaFile>,
    var status: DailyReportStatus
)
