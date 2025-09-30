package com.example.android_project.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_project.ui.theme.AndroidProjectTheme
import com.example.android_project.ui.pantallas.Inicio
import com.example.android_project.ui.pantallas.PantallaAlimentacion
import com.example.android_project.ui.pantallas.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavegacion()
                }
            }
        }
    }
}

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { Home() }
        composable("inicio") { Inicio(navController) }
        composable("login") { PantallaLogin() }
        composable("alimentacion") { PantallaAlimentacion() }
    }
}

@Composable
fun PantallaLogin() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            text = "Pantalla de Login",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
