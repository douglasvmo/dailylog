package com.example.diariodeobra.dailylog

import com.example.diariodeobra.domain.model.Climate
import com.example.diariodeobra.domain.model.MediaFile
import java.util.UUID

data class DailyReportFormState(
    val id: String? = null,
    val workId: String = "",
    val title: String = "",
    val climate: Climate? = null,
    val activities: String = "",
    val occurrences: String? = null,
    val medias: List<MediaFile> = emptyList(),
    val progress: Float = 0f,
    val isValid: Boolean = false,
    val bottomSheetType: BottomSheetType = BottomSheetType.NONE
) {
    enum class BottomSheetType {
        NONE,
        EDIT_CLIMATE,
        EDIT_ACTIVITIES,
        EDIT_OCCURRENCES,
        EDIT_MEDIA_FILE

    }
}