package com.example.diariodeobra.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diariodeobra.dailylog.DailyLogIntent
import com.example.diariodeobra.dailylog.DailyLogState
import com.example.diariodeobra.domain.model.Climate
import com.example.diariodeobra.domain.model.Photo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyLogScreen(
    state: DailyLogState,
    onIntent: (DailyLogIntent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Diário de Obra • Hoje") }
            )
        },
        bottomBar = {
            DailyLogBottomBar(
                enabled = state.isValid,
                onFinish = { onIntent(DailyLogIntent.Finalize) }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            DailyLogProgress(progress = state.progress)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {
                    ClimateCard(
                        climate = state.climate,
                        onClick = {

                        }
                    )
                }

                item {
                    ActivitiesCard(
                        activities = state.activities,
                        onClick = {
                            onIntent(DailyLogIntent.EditActivities)
                        }
                    )
                }

                item {
                    OccurrencesCard(
                        occurrences = state.occurrences,
                        onClick = {
                            onIntent(DailyLogIntent.EditOccurrences)
                        }
                    )
                }

                item {
                    PhotosCard(
                        photos = state.photos,
                        onClick = {
                            onIntent(DailyLogIntent.AddPhoto)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DailyLogProgress(progress: Float) {
    Column(Modifier.padding(16.dp)) {
        Text(
            text = "Progresso do relatório",
            style = MaterialTheme.typography.labelMedium
        )
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SectionCard(
    title: String,
    subtitle: String,
    status: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(subtitle, style = MaterialTheme.typography.bodySmall)
            }

            Icon(
                imageVector = if (status) Icons.Default.Check else Icons.Default.Warning,
                contentDescription = null
            )
        }
    }
}


@Composable
fun ClimateCard(
    climate: Climate?,
    onClick: () -> Unit
) {
    SectionCard(
        title = "Condições do dia",
        subtitle = climate?.name ?: "Não informado",
        status = climate != null,
        onClick = onClick
    )
}

@Composable
fun OccurrencesCard(
    occurrences: String?,
    onClick: () -> Unit
) {
    SectionCard(
        title = "Ocorrências",
        subtitle = occurrences ?: "Nenhuma ocorrência",
        status = true,
        onClick = onClick
    )
}

@Composable
fun ActivitiesCard(
    activities: String,
    onClick: () -> Unit
) {
    SectionCard(
        title = "Atividades executadas",
        subtitle = if (activities.isNotBlank())
            activities.take(60)
        else
            "Nenhuma atividade informada",
        status = activities.isNotBlank(),
        onClick = onClick
    )
}

@Composable
fun DailyLogBottomBar(
    enabled: Boolean,
    onFinish: () -> Unit
) {
    Button(
        onClick = onFinish,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Finalizar relatório")
    }
}

@Composable
fun PhotosCard(
    photos: List<Photo>,
    onClick: () -> Unit
) {
    SectionCard(
        title = "Fotografias",
        subtitle = "${photos.size} foto(s)",
        status = photos.isNotEmpty(),
        onClick = onClick
    )
}

@Preview
@Composable
fun DailyLogScreenPreview(){

    DailyLogScreen(
        state = DailyLogState(),
        onIntent = {}

    )
}