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
import com.example.diariodeobra.dailylog.DailyReportFormEffect
import com.example.diariodeobra.dailylog.DailyReportFormIntent
import com.example.diariodeobra.dailylog.DailyReportFormViewModel
import com.example.diariodeobra.dailyreportlist.DailyReportListIntent
import com.example.diariodeobra.ui.screens.DailyReportFormScreen
import com.example.diariodeobra.ui.screens.LoginScreen
import com.example.diariodeobra.ui.screens.DailyReportListScreen
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
        val dailyReportListViewModel = DailyReportListViewModel(repository)
        val dailyReportFormViewModel = DailyReportFormViewModel(repository)

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
                            LaunchedEffect(Unit) {
                                dailyReportListViewModel.dispath(DailyReportListIntent.LoadList)
                            }

                            val state by dailyReportListViewModel.state.collectAsState()

                            DailyReportListScreen(
                                state,
                                onNewReport = {
                                    navController.navigate("daily-report-new-one")
                                },
                                onReportSelected = { id ->
                                    navController.navigate("daily-reports/${id}")
                                },
                            )
                        }
                        composable("daily-report-new-one") {
                            val state by dailyReportFormViewModel.state.collectAsState()

                            LaunchedEffect(Unit) {
                                dailyReportFormViewModel.dispatch(DailyReportFormIntent.Reset)
                            }

                            DailyReportFormScreen(
                                state = state,
                                onIntent = { dailyReportFormViewModel.dispatch(it)}
                            )

                            LaunchedEffect(Unit){
                                dailyReportFormViewModel.effect.collect{
                                    when(it){
                                        DailyReportFormEffect.NavigateBack -> navController.popBackStack()
                                    }
                                }
                            }

                        }
                        composable("daily-reports/{workId}") {
                            val state by dailyReportFormViewModel.state.collectAsState()

                            LaunchedEffect(Unit) {
                                val workId = it.arguments?.getString("workId")
                                if (workId != null) {
                                    dailyReportFormViewModel.dispatch(DailyReportFormIntent.LoadDailyReport(workId))
                                }
                            }

                            DailyReportFormScreen(
                                state = state,
                                onIntent = { dailyReportFormViewModel.dispatch(it) }
                            )

                            LaunchedEffect(Unit){
                                dailyReportFormViewModel.effect.collect{
                                    when(it){
                                        DailyReportFormEffect.NavigateBack -> navController.popBackStack()
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}