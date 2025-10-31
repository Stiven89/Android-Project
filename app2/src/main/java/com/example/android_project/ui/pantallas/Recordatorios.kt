package com.example.android_project.ui.pantallas

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android_project.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Recordatorios(navController: NavController) {
    var detalle by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    val calendar = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            selectedDate = dateFormatter.format(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tareas",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                }

                Image(
                    painter = painterResource(id = R.drawable.logo_petcare),
                    contentDescription = "Logo PetCare",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd)
                        .padding(end = 10.dp)
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, selected = "recordatorios")
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.recordatorio_superior),
                contentDescription = "Tareas",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Detalles", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = detalle,
                onValueChange = { detalle = it },
                placeholder = { Text("Tipo (vacuna, cita, medicamento)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Nunca olvides una vacuna, cita médica o medicamento. Configura tareas personalizadas para cada mascota.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { datePickerDialog.show() },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(Icons.Filled.DateRange, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (selectedDate.isNotEmpty()) selectedDate else "Seleccionar fecha",
                    color = if (selectedDate.isNotEmpty()) Color.Black else Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        if (detalle.isNotEmpty() && selectedDate.isNotEmpty()) {
                            val currentUser = FirebaseAuth.getInstance().currentUser
                            val userId = currentUser?.uid

                            if (userId != null) {
                                val tarea = hashMapOf(
                                    "descripcion" to detalle,
                                    "fecha" to selectedDate,
                                    "completado" to false,
                                    "timestamp" to System.currentTimeMillis()
                                )

                                db.collection("usuarios")
                                    .document(userId)
                                    .collection("agendas")
                                    .add(tarea)
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "Tarea guardada exitosamente", Toast.LENGTH_SHORT).show()
                                        detalle = ""
                                        selectedDate = ""
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                Toast.makeText(context, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF37A1F8)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(2f)
                        .height(48.dp)
                ) {
                    Text("Guardar cambios", color = Color.White)
                }

                Button(
                    onClick = { navController.navigate("agenda") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Text("Lista", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, selected: String) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Inicio") },
            selected = selected == "inicio",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
            label = { Text("Alimentación") },
            selected = selected == "alimentacion",
            onClick = {
                navController.navigate("alimentacion") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Place, contentDescription = null) },
            label = { Text("Rutas") },
            selected = selected == "rutas",
            onClick = {
                navController.navigate("rutas") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
            label = { Text("Tareas") },
            selected = selected == "recordatorios",
            onClick = {
                navController.navigate("recordatorios") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
