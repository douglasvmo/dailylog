package com.example.diariodeobra.domain.model

enum class DailyReportStatus {
    DRAFT,       // Em preenchimento (não finalizado)
    COMPLETED,   // Finalizado pelo usuário
    SYNCED       // Sincronizado com o servidor
}
