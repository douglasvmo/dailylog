package com.example.diariodeobra.dailylog

import com.example.diariodeobra.domain.model.Climate
import com.example.diariodeobra.domain.model.MediaFile
import java.util.UUID

sealed interface DailyReportFormIntent {
    /** Lifecycle */
    data object Reset : DailyReportFormIntent
    data class  LoadDailyReport(val id: String) : DailyReportFormIntent

    /** Climate */
    data object EditClimate : DailyReportFormIntent
    data class SetClimate(val climate: Climate) : DailyReportFormIntent

    /** Activities */
    data object EditActivities : DailyReportFormIntent
    data class UpdateActivities(val text: String) : DailyReportFormIntent

    /** Occurrences */
    data object EditOccurrences : DailyReportFormIntent
    data class UpdateOccurrences(val text: String?) : DailyReportFormIntent

    /** Photos */
    data object EditMedia : DailyReportFormIntent
    data class RemoveMedia(val photoId: String) : DailyReportFormIntent
    data class AddMedia(val media: MediaFile) : DailyReportFormIntent

    data object EditNone: DailyReportFormIntent

    /** Finalize */
    data object Finalize : DailyReportFormIntent
}
