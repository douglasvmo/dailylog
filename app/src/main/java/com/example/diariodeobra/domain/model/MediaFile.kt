package com.example.diariodeobra.domain.model

import java.util.UUID

data class MediaFile(
    var id: String? = null,
    var path: String = "",
    var checksum: String = "",
    var description: String? = null
)
