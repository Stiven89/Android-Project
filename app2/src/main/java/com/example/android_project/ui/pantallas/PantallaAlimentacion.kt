package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.android_project.R

@Composable
fun PantallaAlimentacion(navController: NavController) {
    // Estados
    val tipoAlimento = remember { mutableStateOf("") }
    val cantidad = remember { mutableStateOf("1") }
    val unidad = remember { mutableStateOf("Gramos") }
    val notas = remember { mutableStateOf("") }

    val expandedCantidad = remember { mutableStateOf(false) }
    val expandedUnidad = remember { mutableStateOf(false) }

    val opcionesCantidad = listOf("1", "2", "3", "4", "5")
    val opcionesUnidades = listOf("Gramos", "Tazas")

    val azulGuardar = Color(0xFF37A1F8)

    Scaffold(
        topBar = {
            // Barra superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Alimentación", style = MaterialTheme.typography.titleLarge)

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                }
            }
        },
        bottomBar = {
            BottomNavigationBarAlimentacion(navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen + campo tipo alimento
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food_example),
                    contentDescription = "Alimento",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(32.dp))
                )

                OutlinedTextField(
                    value = tipoAlimento.value,
                    onValueChange = { tipoAlimento.value = it },
                    label = { Text("Tipo de alimento") },
                    placeholder = { Text("pienso, húmeda, casera") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Cantidad en gramos o tazas", style = MaterialTheme.typography.titleMedium)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.width(300.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Selector de cantidad
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(onClick = { expandedCantidad.value = true }) {
                            Text("Qty: ${cantidad.value}")
                        }
                        DropdownMenu(
                            expanded = expandedCantidad.value,
                            onDismissRequest = { expandedCantidad.value = false }
                        ) {
                            opcionesCantidad.forEach { opcion ->
                                DropdownMenuItem(
                                    text = { Text(opcion) },
                                    onClick = {
                                        cantidad.value = opcion
                                        expandedCantidad.value = false
                                    }
                                )
                            }
                        }
                    }

                    // Selector de unidad
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(onClick = { expandedUnidad.value = true }) {
                            Text(unidad.value)
                        }
                        DropdownMenu(
                            expanded = expandedUnidad.value,
                            onDismissRequest = { expandedUnidad.value = false }
                        ) {
                            opcionesUnidades.forEach { opcion ->
                                DropdownMenuItem(
                                    text = { Text(opcion) },
                                    onClick = {
                                        unidad.value = opcion
                                        expandedUnidad.value = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Notas", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = notas.value,
                onValueChange = { notas.value = it },
                placeholder = { Text("Escribe tus notas aquí") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Horarios de comida (mañana, tarde, noche)", style = MaterialTheme.typography.titleMedium)
            Text("Registra los horarios, tipo y cantidad de alimento para mantener una dieta saludable.")

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(onClick = { /* futuro selector de fecha y hora */ }) {
                Text("Fecha y Hora")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = { /* acción guardar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = azulGuardar)
                ) {
                    Text("Guardar Cambios", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBarAlimentacion(navController: NavController) {
    val currentRoute = navController.currentDestination?.route

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFF4DA6FF)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Alimentación") },
            label = { Text("Alimentación") },
            selected = currentRoute == "alimentacion",
            onClick = {
                navController.navigate("alimentacion") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Place, contentDescription = "Rutas") },
            label = { Text("Rutas") },
            selected = currentRoute == "rutas",
            onClick = {
                navController.navigate("rutas") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Recordatorios") },
            label = { Text("Recordatorios") },
            selected = currentRoute == "recordatorios",
            onClick = {
                navController.navigate("recordatorios") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
