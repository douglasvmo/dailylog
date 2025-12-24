package com.example.diariodeobra.dailylog

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DailyLogViewModel(): ViewModel() {

    private val _state = MutableStateFlow(DailyLogState())
    val state: StateFlow<DailyLogState> = _state

    fun onIntent(intent: DailyLogIntent) {

    }

    private fun finalize() {

    }

    private fun update(block: (DailyLogState) -> DailyLogState) {
        _state.value = block(_state.value)
    }
}
