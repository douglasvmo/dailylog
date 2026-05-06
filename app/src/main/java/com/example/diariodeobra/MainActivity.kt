package com.example.diariodeobra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diariodeobra.dailylog.DailyLogState
import com.example.diariodeobra.dailyreportlist.DailyReportListIntent
import com.example.diariodeobra.ui.screens.DailyLogScreen
import com.example.diariodeobra.ui.screens.LoginScreen
import com.example.diariodeobra.dailyreportlist.DailyReportListScreen
import com.example.diariodeobra.ui.theme.DiarioDeObraTheme
import com.example.diariodeobra.dailyreportlist.DailyReportListViewModel
import com.example.diariodeobra.data.DatabaseProvider
import com.example.diariodeobra.data.repository.DailyReportRoomRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = DatabaseProvider.getDatabase(this)

        val repository = DailyReportRoomRepository(db.dailyReportDao())

        setContent {

            DiarioDeObraTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(onLoginClick = { email, password ->
                                navController.navigate("daily-reports")
                            })
                        }
                        composable("daily-reports") {
                            val dailyReportListViewModel = DailyReportListViewModel(repository)

                            LaunchedEffect(Unit) {
                                dailyReportListViewModel.dispath(DailyReportListIntent.LoadList)
                            }

                            val state by dailyReportListViewModel.state.collectAsState()

                            DailyReportListScreen(
                                state,
                                onNewReport = {
                                    navController.navigate("new-daily-report")
                                },
                                onReportSelected = { id ->
                                    navController.navigate("daily-reports/${id}")
                                },
                            )
                        }
                        composable("new-daily-report") {
                            DailyLogScreen(
                                state = DailyLogState(),
                                onIntent = {}
                            )
                        }
                        composable("daily-reports/{workId}") {
                            DailyLogScreen(
                                state = DailyLogState(),
                                onIntent = {}
                            )
                        }

                    }
                }
            }
        }
    }
}