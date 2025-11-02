package com.example.android_project.ui.pantallas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.android_project.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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

@Composable
fun Rutas(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val currentUser = auth.currentUser

    var direccionUsuario by remember { mutableStateOf("") }
    var ciudadUsuario by remember { mutableStateOf("Bogotá") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(currentUser?.uid) {
        if (currentUser != null) {
            firestore.collection("users")
                .document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val direccion = document.getString("direccion") ?: ""
                        val ciudad = document.getString("ciudad") ?: "Bogotá"
                        val localidad = document.getString("localidad") ?: ""

                        direccionUsuario = if (direccion.isNotEmpty()) {
                            "$direccion, $localidad, $ciudad"
                        } else {
                            "No has configurado tu dirección"
                        }
                        ciudadUsuario = ciudad
                    } else {
                        direccionUsuario = "No has configurado tu dirección"
                    }
                    isLoading = false
                }
                .addOnFailureListener {
                    direccionUsuario = "Error al cargar dirección"
                    isLoading = false
                }
        } else {
            direccionUsuario = "Debes iniciar sesión"
            isLoading = false
        }
    }

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
                DireccionActualSection(
                    direccion = direccionUsuario,
                    isLoading = isLoading,
                    onBuscarVeterinarias = {
                        val query = if (direccionUsuario != "No has configurado tu dirección" &&
                            direccionUsuario != "Debes iniciar sesión") {
                            "veterinarias cerca de $direccionUsuario"
                        } else {
                            "veterinarias en $ciudadUsuario"
                        }

                        val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(query)}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(mapIntent)
                    },
                    onEditarDireccion = {
                        navController.navigate("perfil_edit")
                    }
                )
            }

            item {
                Text(
                    "Veterinarias Recomendadas",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 12.dp)
                )
            }

            items(veterinarias.size) { index ->
                VeterinariaCard(veterinarias[index])
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
fun DireccionActualSection(
    direccion: String,
    isLoading: Boolean,
    onBuscarVeterinarias: () -> Unit,
    onEditarDireccion: () -> Unit
) {
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
                "Tu Dirección",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Dirección:",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        direccion,
                        fontSize = 9.sp,
                        color = if (direccion.contains("No has") || direccion.contains("Error"))
                            Color.Red else Color.Gray
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onEditarDireccion,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = "Editar dirección",
                                tint = Color.Gray,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Button(
                        onClick = onBuscarVeterinarias,
                        modifier = Modifier.height(32.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF03A9F4)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Buscar",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "Buscar",
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }
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

                Button(
                    onClick = {
                        val gmmIntentUri = Uri.parse("geo:${veterinaria.latitud},${veterinaria.longitud}?q=${veterinaria.latitud},${veterinaria.longitud}(${Uri.encode(veterinaria.nombre)})")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")

                        if (mapIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(mapIntent)
                        } else {
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