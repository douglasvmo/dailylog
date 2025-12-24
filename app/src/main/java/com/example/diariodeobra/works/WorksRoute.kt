package com.example.diariodeobra.works

import androidx.compose.runtime.Composable
import com.example.diariodeobra.ui.screens.WorksScreen

@Composable
fun WorksRoute(){
    WorksScreen(
        state = WorksState(),
        onWorkSelected = {}
    )
}