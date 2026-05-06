package com.example.diariodeobra.dailyreportlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diariodeobra.domain.model.Climate
import com.example.diariodeobra.domain.model.DailyReport
import com.example.diariodeobra.domain.model.DailyReportStatus
import com.example.diariodeobra.ui.components.CreateDailyReportBottomSheet
import java.time.LocalDate

@Composable
fun DailyReportListScreen(
    state: DailyReportListState,
    onNewReport: () -> Unit,
    onReportSelected: (String) -> Unit,
) {

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onNewReport() },
                icon = { Icon(Icons.Default.Add, "Novo Pedido") },
                text = { Text("Novo Relatório") },
            )
        }
    ) { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            items(state.list) { report ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable { report.id?.let { onReportSelected(it) } }) {

                    Column(Modifier.padding(16.dp)) {
                        Text(report.title, style = MaterialTheme.typography.titleLarge)
                        Text(report.date.toString(), style = MaterialTheme.typography.bodyLarge)

                        report.occurrences?.let {
                            Text(
                                it,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    DailyReportListScreen(
        state = DailyReportListState(
            list = listOf(
                DailyReport(
                    id = "1",
                    title = "Inspessao X Teste",
                    date = LocalDate.now(),
                    status = DailyReportStatus.DRAFT,
                    climate = Climate.CLOUDY,
                    activities = "Teste",
                    occurrences = "Teste",
                    photos = emptyList(),
                    workId = "",
                ),
                DailyReport(
                    id = "1",
                    title = "Registro X Teste",
                    date = LocalDate.now(),
                    status = DailyReportStatus.DRAFT,
                    climate = Climate.CLOUDY,
                    activities = "Teste",
                    occurrences = "Teste",
                    photos = emptyList(),
                    workId = "",
                )
            )
        ),
        onNewReport = {},
        onReportSelected = {},
    )
}