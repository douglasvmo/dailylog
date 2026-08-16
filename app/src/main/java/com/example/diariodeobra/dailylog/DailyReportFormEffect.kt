package com.example.diariodeobra.dailylog

sealed interface DailyReportFormEffect {
    object NavigateBack : DailyReportFormEffect
}