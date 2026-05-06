package com.example.diariodeobra.domain.model

import android.R
import java.time.LocalDate
import java.util.UUID

data class DailyReport(
    val id: String? = null,
    var workId: String,
    var date: LocalDate,
    var climate: Climate?,
    var title: String,
    var activities: String,
    var occurrences: String?,
    var photos: List<Photo>,
    var status: DailyReportStatus
)
