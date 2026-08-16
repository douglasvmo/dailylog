package com.example.diariodeobra.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diariodeobra.dailylog.DailyReportFormIntent
import com.example.diariodeobra.dailylog.DailyReportFormState
import com.example.diariodeobra.domain.model.Climate
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyReportEditBottomSheet(
    state: DailyReportFormState,
    onIntent: (DailyReportFormIntent) -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onIntent(DailyReportFormIntent.EditNone) },
    ) {
        DailyReportSheetContent(state, onIntent)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyReportSheetContent(
    state: DailyReportFormState,
    onIntent: (DailyReportFormIntent) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val focus = remember { FocusRequester() }

        when (state.bottomSheetType) {
            DailyReportFormState.BottomSheetType.NONE -> null
            DailyReportFormState.BottomSheetType.EDIT_CLIMATE -> {
                Column {
                    Text(
                        "Condições do dia",
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Center
                    )

                    Climate.entries.map {
                        Row(Modifier
                            .fillMaxWidth()
                            .padding(2.dp), horizontalArrangement = Arrangement.Center)  {
                                Text(it.name)
                        }
                    }

                }
            }

            DailyReportFormState.BottomSheetType.EDIT_ACTIVITIES -> {
                Text(
                    "Atividades Executadas",
                    Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = state.activities,
                    onValueChange = { onIntent(DailyReportFormIntent.UpdateActivities(it)) },
                    label = { Text("Atividades") },
                    placeholder = { Text("Ex: Treinamento") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRestorer(focus),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onIntent(DailyReportFormIntent.EditNone)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                )

                LaunchedEffect(Unit) {
                    delay(300)
                    focus.requestFocus()
                }
            }

            DailyReportFormState.BottomSheetType.EDIT_OCCURRENCES -> {
                Text(
                    "Ocorrências",
                    Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = state.occurrences ?: "",
                    onValueChange = { onIntent(DailyReportFormIntent.UpdateOccurrences(it)) },
                    label = { Text("Ocorrências") },
                    placeholder = { Text("Estabelecimento fechado") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onIntent(DailyReportFormIntent.EditNone)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    )
                )
            }

            DailyReportFormState.BottomSheetType.EDIT_MEDIA_FILE -> {
                Text(
                    "Evidencias",
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )
                state.medias.map {
                    val iconImage = if (it.path.endsWith("mp4")) Icons.Default.VideoFile else Icons.Default.Photo
                    val description = if (it.path.endsWith("mp4")) "Video" else "Imagem"
                    val fileName = it.path.substringAfterLast("/")

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    )
                    {
                        Icon(
                            iconImage,
                            contentDescription = description,
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            fileName,
                            Modifier
                                .weight(1f)
                                .padding(start = 10.dp),
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = description,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {
                                    if (it.id != null) {
                                        onIntent(DailyReportFormIntent.RemoveMedia(it.id!!))
                                    }
                                },
                            tint = MaterialTheme.colorScheme.secondary

                        )
                    }
                }
                if (state.medias.isEmpty()) {
                    Text(
                        "Nenhuma evidencia encontrada.",
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        "Clique no botão abaixo para adicionar evidências",
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onIntent(DailyReportFormIntent.EditMedia) },
                    modifier = Modifier.fillMaxWidth(),
                    content = { Text("Adicionar Evidencia") }
                )
            }
        }
    }

}

@Preview
@Composable
fun CreateDailyReportBottomSheetPreview() {
    MaterialTheme {
        DailyReportSheetContent(
            state = DailyReportFormState(
                bottomSheetType = DailyReportFormState.BottomSheetType.EDIT_MEDIA_FILE,
                medias = emptyList(),
            ),
            onIntent = {}
        )
    }
}