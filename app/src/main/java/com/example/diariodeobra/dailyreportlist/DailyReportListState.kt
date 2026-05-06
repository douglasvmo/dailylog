package com.example.diariodeobra.dailyreportlist

import com.example.diariodeobra.domain.model.DailyReport

data class DailyReportListState (
    var isLoading: Boolean = true,
    var showCreateReportModal: Boolean = false,
    var list: List<DailyReport> = emptyList()
)