package com.example.diariodeobra.domain.model

import java.util.UUID

data class Work(
    var id: String? = null,
    var description: String = "",
    var address: String = "",
    var clientName: String = ""
)