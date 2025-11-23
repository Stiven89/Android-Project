package com.example.android_project.ui.pantallas

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.android_project.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

data class RegistroAlimentacion(
    val id: String = "",
    val tipo: String = "",
    val cantidad: String = "",
    val unidad: String = "",
    val notas: String = ""
)

@Composable
fun PantallaAlimentacion(navController: NavController) {
    val firestore = FirebaseFirestore.getInstance()
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val context = LocalContext.current

    var tipoAlimento by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var unidad by remember { mutableStateOf("Gramos") }
    var notas by remember { mutableStateOf("") }

    var expandedUnidad by remember { mutableStateOf(false) }

    val opcionesUnidades = listOf(
        "Gramos", "Tazas", "Mililitros", "Miligramos", "Kilogramos",
        "Porciones", "Latas", "Cucharadas", "Cucharaditas"
    )

    val azulGuardar = Color(0xFF37A1F8)

    var registros by remember { mutableStateOf(listOf<RegistroAlimentacion>()) }
    var isLoading by remember { mutableStateOf(true) }

    // registros existentes
    LaunchedEffect(userId) {
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("alimentacion")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Toast.makeText(context, "Error al cargar registros", Toast.LENGTH_SHORT).show()
                        isLoading = false
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        registros = snapshot.documents.map { doc ->
                            RegistroAlimentacion(
                                id = doc.id,
                                tipo = doc.getString("tipo") ?: "",
                                cantidad = doc.getString("cantidad") ?: "",
                                unidad = doc.getString("unidad") ?: "",
                                notas = doc.getString("notas") ?: ""
                            )
                        }
                    }
                    isLoading = false
                }
        } else isLoading = false
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth().height(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Alimentación", style = MaterialTheme.typography.titleLarge)

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        },
        bottomBar = {
            BottomNavigationBaralimentacion(navController = navController, selected = "alimentacion")
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            // Imagen + tipo de alimento
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food_example),
                    contentDescription = "Alimento",
                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(32.dp))
                )

                OutlinedTextField(
                    value = tipoAlimento,
                    onValueChange = { tipoAlimento = it },
                    label = { Text("Tipo de alimento") },
                    placeholder = { Text("Tipo de alimento") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // CANTIDAD
            Text("Cantidad", style = MaterialTheme.typography.titleMedium)

            OutlinedTextField(
                value = cantidad,
                onValueChange = { nueva ->
                    if (nueva.all { it.isDigit() }) cantidad = nueva
                },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // UNIDAD
            Text("Unidad", style = MaterialTheme.typography.titleMedium)

            Box {
                OutlinedButton(
                    onClick = { expandedUnidad = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(unidad)
                }

                DropdownMenu(
                    expanded = expandedUnidad,
                    onDismissRequest = { expandedUnidad = false }
                ) {
                    opcionesUnidades.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                unidad = opcion
                                expandedUnidad = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // NOTAS
            Text("Notas", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = notas,
                onValueChange = { notas = it },
                placeholder = { Text("Escribe tus notas aquí") },
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // GUARDAR
            Button(
                onClick = {
                    if (tipoAlimento.isBlank()) {
                        Toast.makeText(context, "Ingresa el tipo de alimento", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (cantidad.isBlank()) {
                        Toast.makeText(context, "Ingresa una cantidad válida", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (userId == null) {
                        Toast.makeText(context, "Debes iniciar sesión", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val registro = hashMapOf(
                        "tipo" to tipoAlimento,
                        "cantidad" to cantidad,
                        "unidad" to unidad,
                        "notas" to notas,
                        "timestamp" to System.currentTimeMillis()
                    )

                    firestore.collection("users")
                        .document(userId)
                        .collection("alimentacion")
                        .add(registro)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Registro guardado", Toast.LENGTH_SHORT).show()
                            tipoAlimento = ""
                            cantidad = ""
                            notas = ""
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                },
                colors = ButtonDefaults.buttonColors(containerColor = azulGuardar),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar", color = Color.White)
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text("Registros Guardados", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                registros.isEmpty() -> Text("Aún no hay registros.", color = Color.Gray)

                else -> Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    registros.forEach { registro ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFDDEBFF))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("🍽 ${registro.tipo}", style = MaterialTheme.typography.bodyLarge)
                                Text("Cantidad: ${registro.cantidad} ${registro.unidad}")
                                if (registro.notas.isNotEmpty()) {
                                    Text("Notas: ${registro.notas}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBaralimentacion(navController: NavController, selected: String) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Inicio") },
            selected = selected == "inicio",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, null) },
            label = { Text("Alimentación") },
            selected = selected == "alimentacion",
            onClick = { navController.navigate("alimentacion") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Place, null) },
            label = { Text("Rutas") },
            selected = selected == "rutas",
            onClick = { navController.navigate("rutas") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, null) },
            label = { Text("Tareas") },
            selected = selected == "recordatorios",
            onClick = { navController.navigate("recordatorios") }
        )
    }
}
