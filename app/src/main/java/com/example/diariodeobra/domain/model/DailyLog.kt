package com.example.diariodeobra.domain.model

import java.time.LocalDate

data class DailyLog(
    val id: String,
    val workId: String,
    val date: LocalDate,
    val climate: Climate?,
    val activities: String,
    val occurrences: String?,
    val photos: List<Photo>,
    val status: DailyLogStatus
)
