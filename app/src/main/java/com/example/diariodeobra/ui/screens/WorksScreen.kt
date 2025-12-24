package com.example.diariodeobra.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diariodeobra.domain.model.Work
import com.example.diariodeobra.works.WorksState

@Composable
fun WorksScreen(
    state: WorksState,
    onWorkSelected: (String) -> Unit
) {

    Scaffold { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            items(state.works) { work ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable { onWorkSelected(work.id) }) {

                    Column(Modifier.padding(16.dp)) {
                        Text(work.description, style = MaterialTheme.typography.titleLarge)
                        Text(work.clientName, style = MaterialTheme.typography.bodyLarge)
                        Text(work.address, style = MaterialTheme.typography.bodySmall)
                    }
                }

            }
        }
    }
}



@Preview
@Composable
fun Preview(){
    WorksScreen(
        state = WorksState(
            works = listOf(
                Work(
                    id = "1",
                    description = "Obra 1",
                    address = "Rua A, 123",
                    clientName = "Cliente 1"
                ),
                Work(
                    id = "2",
                    description = "Obra 2",
                    address = "Rua B, 456",
                    clientName = "Cliente 2"
                )
            )
        ),
        onWorkSelected = {}
    )
}