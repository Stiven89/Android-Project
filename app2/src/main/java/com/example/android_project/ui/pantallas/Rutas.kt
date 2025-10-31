package com.example.android_project.ui.pantallas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.android_project.R

data class Veterinaria(
    val nombre: String,
    val nivel: String,
    val telefono: String,
    val imagen: Int,
    val rating: Int,
    val direccion: String,
    val latitud: Double,
    val longitud: Double
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RutasPreview() {
    val navController = rememberNavController()
    Rutas(navController)
}

@Composable
fun Rutas(navController: NavController) {
    val veterinarias = listOf(
        Veterinaria(
            nombre = "Mascotas Pet",
            nivel = "calificación",
            telefono = "Teléfono: 317 3275656",
            imagen = R.drawable.rutas1,
            rating = 5,
            direccion = "Calle 85 #15-20, Bogotá",
            latitud = 4.6686,
            longitud = -74.0568
        ),
        Veterinaria(
            nombre = "Dog Tor Vet",
            nivel = "calificación",
            telefono = "Teléfono: 319 6880254",
            imagen = R.drawable.rutas2,
            rating = 5,
            direccion = "Carrera 7 #89-32, Bogotá",
            latitud = 4.6800,
            longitud = -74.0500
        )
    )

    Scaffold(
        topBar = { VeterinariaTopBar(navController) },
        bottomBar = { BottomNavigationBarRutas(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                DireccionActualSection()
            }

            item {
                Text(
                    "Lista de Resultados",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 12.dp)
                )
            }

            items(veterinarias) { veterinaria ->
                VeterinariaCard(veterinaria)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinariaTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Veterinarias",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )
}

@Composable
fun DireccionActualSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = null,
                tint = Color(0xFF03A9F4),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Dirección Actual",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Dirección:",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text("Cra 7ta. No 89-C32,", fontSize = 9.sp, color = Color.Gray)
                Text("Carrera 7, Localidad Chapi...", fontSize = 9.sp, color = Color.Gray)
                Text("Contact: +57 312647 1824", fontSize = 9.sp, color = Color.Gray)
            }

            Row {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Editar",
                        tint = Color.Gray,
                        modifier = Modifier.size(14.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .background(Color(0xFF03A9F4), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Agregar",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun VeterinariaCard(veterinaria: Veterinaria) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = veterinaria.imagen),
                contentDescription = veterinaria.nombre,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    veterinaria.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        veterinaria.nivel,
                        fontSize = 11.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    repeat(veterinaria.rating) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(11.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        veterinaria.direccion,
                        fontSize = 9.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))
                Text(veterinaria.telefono, fontSize = 9.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Horario: Abierto las 24 horas", fontSize = 8.sp, fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.height(8.dp))

                // Botón para abrir Google Maps
                Button(
                    onClick = {
                        // Opción 1: Abrir con coordenadas
                        val gmmIntentUri = Uri.parse("geo:${veterinaria.latitud},${veterinaria.longitud}?q=${veterinaria.latitud},${veterinaria.longitud}(${veterinaria.nombre})")

                        // Opción 2 (alternativa): Abrir con dirección
                        // val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(veterinaria.direccion)}")

                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")

                        // Verificar si Google Maps está instalado
                        if (mapIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(mapIntent)
                        } else {
                            // Si no está instalado, abrir en navegador
                            val webIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com/maps/search/?api=1&query=${veterinaria.latitud},${veterinaria.longitud}")
                            )
                            context.startActivity(webIntent)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4285F4)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Cómo llegar",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }

        HorizontalDivider(
            color = Color(0xFFE0E0E0),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Composable
fun BottomNavigationBarRutas(navController: NavController) {
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
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
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
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
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
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Recordatorios") },
            label = { Text("Tareas") },
            selected = currentRoute == "recordatorios",
            onClick = {
                navController.navigate("recordatorios") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}