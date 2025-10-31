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
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Recordatorios(navController: NavController) {
    val detalle = remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance() // 🔹 Conexión a Firestore

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
            // Imagen superior
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
                value = detalle.value,
                onValueChange = { detalle.value = it },
                placeholder = { Text("Tipo (vacuna, cita, medicamento)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Nunca olvides una vacuna, cita médica o medicamento. " +
                        "Configura tareas personalizadas para cada mascota."
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { datePickerDialog.show() },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = if (selectedDate.isNotEmpty()) selectedDate else "Seleccionar fecha",
                        color = Color(0xFF828282)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 Botones Guardar / Lista
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (detalle.value.isNotEmpty() && selectedDate.isNotEmpty()) {
                            val tarea = hashMapOf(
                                "detalle" to detalle.value,
                                "fecha" to selectedDate,
                                "completado" to false
                            )

                            db.collection("tareas")
                                .add(tarea)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show()
                                    detalle.value = ""
                                    selectedDate = ""
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Error al guardar la tarea", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF37A1F8)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(2f)
                ) {
                    Text("Guardar cambios", color = Color.White)
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(
                    onClick = { navController.navigate("agenda") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Lista", color = Color.White)
                }
            }
        }
    }
}

/* 🔹 Barra de navegación inferior reutilizable */
@Composable
fun BottomNavigationBar(navController: NavController, selected: String) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, null) },
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
            icon = { Icon(Icons.Default.Favorite, null) },
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
            icon = { Icon(Icons.Default.Place, null) },
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
            icon = { Icon(Icons.Default.Notifications, null) },
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


/*package com.example.android_project.ui.pantallas

import android.app.DatePickerDialog
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_project.R
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Toast


@Composable
fun Recordatorios(navController: NavController) {
    val detalle = remember { mutableStateOf("") }
    //var selected by remember { mutableStateOf("2") } // Botón seleccionado
    val context = LocalContext.current
    // Estado para guardar la fecha seleccionada
    var selectedDate by remember { mutableStateOf("") }
    val db = FirebaseFirestore.getInstance()

    // Calendario
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

                // Botón atrás
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                }

                // Logo lateral
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
            //BottomNavigationBar(navController = navController, selected = "recordatorios")
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen superior
            Image(
                painter = painterResource(id = R.drawable.recordatorio_superior),
                contentDescription = "Tareas",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Detalles
            Text("Detalles", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = detalle.value,
                onValueChange = { detalle.value = it },
                placeholder = { Text("Tipo (vacuna, cita, medicamento)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto informativo
            Text(
                "Nunca olvides una vacuna, cita médica o medicamento. " +
                        "Configura tareas personalizados para cada mascota "
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de fecha y notas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                //verticalAlignment = Alignment.CenterVertically
            )  {
                Button(
                    onClick = {
                        if (detalle.value.isNotEmpty() && selectedDate.isNotEmpty()) {
                            val tarea = hashMapOf(
                                "detalle" to detalle.value,
                                "fecha" to selectedDate,
                                "completado" to false
                            )
                            db.collection("tareas")
                                .add(tarea)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Tarea guardada", Toast.LENGTH_SHORT).show()
                                    detalle.value = ""
                                    selectedDate = ""
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Error al guardar", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF37A1F8)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(2f)
                ) {
                    Text("Guardar cambios", color = Color.White)
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(
                    onClick = { navController.navigate("agenda") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Lista", color = Color.White)
                }
            }
        }
    }
}

             {
                OutlinedButton(
                    onClick = { datePickerDialog.show() },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = if (selectedDate.isNotEmpty()) selectedDate else "Seleccionar fecha",
                        color = Color(0xFF828282)
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botones Guardar / Lista
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Acción guardar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF37A1F8)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(2f)
                ) {
                    Text("Guardar cambios", color = Color.White)
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(
                    onClick = { navController.navigate("agenda") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Lista", color = Color.White)
                }
            }
        }
    }
}

/* 🔹 Barra de navegación inferior reutilizable entre pantallas */
@Composable
fun BottomNavigationBar(navController: NavController, selected: String) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, null) },
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
            icon = { Icon(Icons.Default.Favorite, null) },
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
            icon = { Icon(Icons.Default.Place, null) },
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
            icon = { Icon(Icons.Default.Notifications, null) },
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
} */
