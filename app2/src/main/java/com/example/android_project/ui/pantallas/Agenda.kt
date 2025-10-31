package com.example.android_project.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class Tarea(
    val id: String = "",
    val descripcion: String = "",
    val fecha: String = "",
    val completado: Boolean = false
)

@Composable
fun Agenda(navController: NavController) {
    val firestore = FirebaseFirestore.getInstance()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    var listaTareas by remember { mutableStateOf(listOf<Tarea>()) }
    var cargando by remember { mutableStateOf(true) }

    // 🔹 Escuchar cambios en Firestore en tiempo real
    LaunchedEffect(userId) {
        if (userId != null) {
            firestore.collection("usuarios")
                .document(userId)
                .collection("agendas")
                .addSnapshotListener { snapshot, _ ->
                    if (snapshot != null) {
                        val tareas = snapshot.documents.mapNotNull {
                            val descripcion = it.getString("descripcion") ?: return@mapNotNull null
                            val fecha = it.getString("fecha") ?: ""
                            val completado = it.getBoolean("completado") ?: false
                            Tarea(id = it.id, descripcion = descripcion, fecha = fecha, completado = completado)
                        }
                        listaTareas = tareas
                        cargando = false
                    }
                }
        }
    }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { navController.navigate("recordatorios") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2B2A29)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Añadir", color = Color.White)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize()
        ) {
            Text("Hoy", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            if (cargando) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                val recordatorios = listaTareas.filter { !it.completado }
                val completados = listaTareas.filter { it.completado }

                if (recordatorios.isNotEmpty()) {
                    Text("RECORDATORIOS", color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn {
                        items(recordatorios.size) { index ->
                            val tarea = recordatorios[index]
                            TareaItem(tarea, userId!!, firestore)
                        }
                    }
                }

                if (completados.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("COMPLETADOS", color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn {
                        items(completados.size) { index ->
                            val tarea = completados[index]
                            TareaItem(tarea, userId!!, firestore)
                        }
                    }
                }

                if (recordatorios.isEmpty() && completados.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No hay tareas registradas.")
                    }
                }
            }
        }
    }
}

@Composable
fun TareaItem(tarea: Tarea, userId: String, firestore: FirebaseFirestore) {
    val colorFondo = if (tarea.completado) Color(0xFFF0F0F0) else Color(0xFFDDEBFF)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = colorFondo)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = tarea.completado,
                    onCheckedChange = { nuevoEstado ->
                        firestore.collection("usuarios")
                            .document(userId)
                            .collection("agendas")
                            .document(tarea.id)
                            .update("completado", nuevoEstado)
                    }
                )

                Column {
                    Text(
                        text = tarea.descripcion,
                        color = if (tarea.completado) Color.Gray else Color.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    if (tarea.fecha.isNotEmpty()) {
                        Text(
                            text = "📅 ${tarea.fecha}",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
