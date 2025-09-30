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
import com.example.android_project.ui.pantallas.Rutas
import com.example.android_project.ui.pantallas.Articulo1
import com.example.android_project.ui.pantallas.Articulo2
import com.example.android_project.ui.pantallas.Crear_cuenta
import com.example.android_project.ui.pantallas.Perfil
import com.example.android_project.ui.pantallas.Recuperar_contrasenia
import com.example.android_project.ui.pantallas.Articulo3
import com.example.android_project.ui.pantallas.Agenda
import com.example.android_project.ui.pantallas.Recordatorios
import com.example.android_project.ui.pantallas.PantallaLogin

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

    NavHost(navController = navController, startDestination = "alimentacion") {
        composable("home") { Home() }
        composable("Rutas") { Rutas() }
        composable("inicio") { Inicio(navController) }
        composable("login") { PantallaLogin(navController) }
        composable("alimentacion") { PantallaAlimentacion() }
        composable("Articulo1") { Articulo1() }
        composable("Articulo2") { Articulo2() }
        composable("Crear_cuenta") { Crear_cuenta() }
        composable("Agenda") { Agenda() }
        composable("Recordatorios") { Recordatorios() }
        composable("Articulo3") { Articulo3() }
        composable("Perfil") { Perfil() }
        composable("Recuperar_contrasenia") { Recuperar_contrasenia() }
    }
}
