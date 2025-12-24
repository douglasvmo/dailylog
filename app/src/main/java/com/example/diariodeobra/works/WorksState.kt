package com.example.diariodeobra.works

import com.example.diariodeobra.domain.model.Work

data class WorksState (
    var isLoading: Boolean = true,
   var works: List<Work> = emptyList()
)