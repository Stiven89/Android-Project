package com.example.android_project.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_project.ui.theme.AndroidProjectTheme
import com.example.android_project.ui.pantallas.*

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

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { Inicio(navController) }
        composable("home") { Home(navController) }
        composable("rutas") { Rutas(navController) }
        composable("login") { PantallaLogin(navController) }
        composable("alimentacion") { PantallaAlimentacion(navController) }
        composable("articulo1") { Articulo1(navController) }
        composable("articulo2") { Articulo2(navController)  }
        composable("articulo3") { Articulo3(navController)}
        composable("crear_cuenta") { Crear_cuenta() }
        composable("agenda") { Agenda(navController) }
        composable("recordatorios") { Recordatorios(navController) }
        composable("perfil") { Perfil(navController) }
        composable("recuperar_contrasenia") { Recuperar_contrasenia() }
    }
}
