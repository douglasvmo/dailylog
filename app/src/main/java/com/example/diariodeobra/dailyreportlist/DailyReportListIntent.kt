package com.example.diariodeobra.dailyreportlist

sealed interface DailyReportListIntent {

    data object LoadList: DailyReportListIntent

}