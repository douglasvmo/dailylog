package com.example.diariodeobra.dailylog

import com.example.diariodeobra.domain.model.Climate
import com.example.diariodeobra.domain.model.Photo

sealed interface DailyLogIntent {
    /** Lifecycle */
    data object LoadToday : DailyLogIntent

    /** Climate */
    data object EditClimate : DailyLogIntent
    data class SetClimate(val climate: Climate) : DailyLogIntent

    /** Activities */
    data object EditActivities : DailyLogIntent
    data class UpdateActivities(val text: String) : DailyLogIntent

    /** Occurrences */
    data object EditOccurrences : DailyLogIntent
    data class UpdateOccurrences(val text: String?) : DailyLogIntent

    /** Photos */
    data object AddPhoto : DailyLogIntent
    data class RemovePhoto(val photoId: String) : DailyLogIntent

    /** Finalize */
    data object Finalize : DailyLogIntent
}
