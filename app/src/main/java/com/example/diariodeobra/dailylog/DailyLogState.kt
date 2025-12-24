package com.example.diariodeobra.dailylog

import com.example.diariodeobra.domain.model.Climate
import com.example.diariodeobra.domain.model.Photo

data class DailyLogState(
    val climate: Climate? = null,
    val activities: String = "",
    val occurrences: String? = null,
    val photos: List<Photo> = emptyList(),
    val progress: Float = 0f,
    val isValid: Boolean = false
)