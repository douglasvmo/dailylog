package com.example.diariodeobra.dailyreportlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diariodeobra.domain.model.DailyReport
import com.example.diariodeobra.domain.repository.DailyReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DailyReportListViewModel(
    private val repository: DailyReportRepository
): ViewModel() {

    private val _state = MutableStateFlow(DailyReportListState())
    val state: StateFlow<DailyReportListState> = _state

    fun dispath(intent: DailyReportListIntent){
        when(intent){
            is DailyReportListIntent.LoadList -> loadList()
        }
    }

    private fun loadList() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.observeReports()
                .collect { setSuccess(it) }
                .runCatching { setError() }
        }
    }
    private fun setError() {
        _state.update {
            it.copy(
                isLoading = false,
                list = emptyList()
            )
        }
    }

    private fun setSuccess(list: List<DailyReport>){
        _state.update {
            it.copy(
                isLoading = false,
                list = list
            )
        }
    }

}


