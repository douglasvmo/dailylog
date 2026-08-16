package com.example.diariodeobra.dailylog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diariodeobra.domain.model.DailyReport
import com.example.diariodeobra.domain.model.DailyReportStatus
import com.example.diariodeobra.domain.repository.DailyReportRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class DailyReportFormViewModel(
    private val repository: DailyReportRepository
): ViewModel() {
    private val _state = MutableStateFlow(DailyReportFormState())
    val state: StateFlow<DailyReportFormState> = _state

    private val _effect = MutableSharedFlow<DailyReportFormEffect>()
    val effect: MutableSharedFlow<DailyReportFormEffect> = _effect


    fun dispatch(intent: DailyReportFormIntent) {
        when (intent) {
            is DailyReportFormIntent.Reset -> update { DailyReportFormState() }
            is DailyReportFormIntent.LoadDailyReport -> loadDailyReport(intent.id)
            is DailyReportFormIntent.EditMedia -> update { it.copy(bottomSheetType = DailyReportFormState.BottomSheetType.EDIT_MEDIA_FILE) }
            is DailyReportFormIntent.EditActivities -> update { it.copy(bottomSheetType = DailyReportFormState.BottomSheetType.EDIT_ACTIVITIES) }
            is DailyReportFormIntent.EditClimate -> update { it.copy(bottomSheetType = DailyReportFormState.BottomSheetType.EDIT_CLIMATE) }
            is DailyReportFormIntent.EditOccurrences -> update { it.copy(bottomSheetType = DailyReportFormState.BottomSheetType.EDIT_OCCURRENCES) }
            is DailyReportFormIntent.EditNone -> update { it.copy(bottomSheetType = DailyReportFormState.BottomSheetType.NONE) }
            is DailyReportFormIntent.Finalize -> finalize()
            is DailyReportFormIntent.RemoveMedia -> update { it.copy(medias = it.medias.filter { p -> p.id != intent.photoId }) }
            is DailyReportFormIntent.SetClimate -> update { it.copy(climate = intent.climate) }
            is DailyReportFormIntent.UpdateActivities -> update { it.copy(activities = intent.text) }
            is DailyReportFormIntent.UpdateOccurrences -> update { it.copy(occurrences = intent.text) }
            is DailyReportFormIntent.AddMedia -> update { it.copy( medias = it.medias.plus(intent.media)) }
        }
    }

    private fun loadDailyReport(id: String) {
        viewModelScope.launch {
            val report = repository.getReport(id)
            if(report != null){
                update {
                    it.copy(
                        id = report.id,
                        title = report.title,
                        climate = report.climate,
                        activities = report.activities,
                        occurrences = report.occurrences,
                        medias = report.medias,
                        workId = report.workId
                    )
                }
            }

        }

    }


    private fun finalize() {
        val report = DailyReport(
            id = _state.value.id,
            workId = _state.value.workId,
            date = LocalDate.now(),
            climate = _state.value.climate,
            title = _state.value.title,
            activities = _state.value.activities,
            occurrences = _state.value.occurrences,
            medias = _state.value.medias,
            status = DailyReportStatus.COMPLETED
        )

        viewModelScope.launch {
            repository.saveReport(report)
            _effect.emit(DailyReportFormEffect.NavigateBack)
        }
    }

    private fun update(block: (DailyReportFormState) -> DailyReportFormState) {
        _state.value = block(_state.value)
    }
}
