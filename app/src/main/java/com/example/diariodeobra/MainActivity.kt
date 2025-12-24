package com.example.diariodeobra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diariodeobra.ui.screens.LoginScreen
import com.example.diariodeobra.works.WorksRoute
import com.example.diariodeobra.ui.theme.DiarioDeObraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            DiarioDeObraTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(onLoginClick = { email, password ->
                                navController.navigate("works")
                            })
                        }
                        composable("works") {
                            WorksRoute()

                        }
                        composable("work/{workId}") {
                            Column() {
                            }
                        }

                    }
                }
            }
        }
    }
}