package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

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
    val scope = rememberCoroutineScope()

    // Estados del formulario
    val tipoAlimento = remember { mutableStateOf("") }
    val cantidad = remember { mutableStateOf("1") }
    val unidad = remember { mutableStateOf("Gramos") }
    val notas = remember { mutableStateOf("") }

    val expandedCantidad = remember { mutableStateOf(false) }
    val expandedUnidad = remember { mutableStateOf(false) }

    val opcionesCantidad = listOf("1", "2", "3", "4", "5")
    val opcionesUnidades = listOf("Gramos", "Tazas")

    val azulGuardar = Color(0xFF37A1F8)

    // Estado para los registros guardados
    var registros by remember { mutableStateOf(listOf<RegistroAlimentacion>()) }

    // 🔹 Cargar los registros guardados del usuario
    LaunchedEffect(userId) {
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("alimentacion")
                .addSnapshotListener { snapshot, _ ->
                    if (snapshot != null) {
                        registros = snapshot.documents.mapNotNull {
                            val tipo = it.getString("tipo") ?: return@mapNotNull null
                            val cantidad = it.getString("cantidad") ?: ""
                            val unidad = it.getString("unidad") ?: ""
                            val notas = it.getString("notas") ?: ""
                            RegistroAlimentacion(
                                id = it.id,
                                tipo = tipo,
                                cantidad = cantidad,
                                unidad = unidad,
                                notas = notas
                            )
                        }
                    }
                }
        }
    }

    Scaffold(
        topBar = {
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
            BottomNavigationBar( navController = navController, selected = "alimentacion")
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

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val tipo = tipoAlimento.value.trim()
                    if (tipo.isNotEmpty() && userId != null) {
                        val nuevaRef = firestore.collection("users")
                            .document(userId)
                            .collection("alimentacion")
                            .document()

                        val registro = RegistroAlimentacion(
                            id = nuevaRef.id,
                            tipo = tipo,
                            cantidad = cantidad.value,
                            unidad = unidad.value,
                            notas = notas.value
                        )

                        scope.launch {
                            nuevaRef.set(registro)
                                .addOnSuccessListener {
                                    tipoAlimento.value = ""
                                    notas.value = ""
                                }
                                .addOnFailureListener {
                                    // Puedes mostrar un Toast si deseas
                                }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = azulGuardar),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Cambios", color = Color.White)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 🔹 Mostrar registros guardados
            Text("Registros Guardados", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            if (registros.isEmpty()) {
                Text("Aún no hay registros de alimentación.")
            } else {
                LazyColumn {
                    items(registros) { registro ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
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
