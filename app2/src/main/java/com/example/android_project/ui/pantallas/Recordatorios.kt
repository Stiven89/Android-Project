package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_project.R

@Composable
fun Recordatorios() {
    val detalle = remember { mutableStateOf("") }
    val notas = remember { mutableStateOf("") }
    var selected by remember { mutableStateOf("2") } //esta quemado en el boton 2

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text ="Recordatorios",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                // Botón atras
                IconButton(
                    onClick = { /* Acción de regreso */ },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                }

                // Logo
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
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Alimentación") },
                    label = { Text("Alimentación") },
                    selected = false,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Place, contentDescription = "Rutas") },
                    label = { Text("Rutas") },
                    selected = false,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Recordatorios") },
                    label = { Text("Recordatorios") },
                    selected = true,
                    onClick = {}
                )
            }
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
                painter = painterResource(id = R.drawable.recordatorio),
                contentDescription = "Recordatorio",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de números
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "RECORDATORIOS",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listOf("1", "2", "3", "4", "+").forEach { label ->
                        OutlinedButton(
                            onClick = { selected = label },
                            modifier = Modifier
                                .weight(1f)
                                .height(45.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = SolidColor(Color(0xFF37A1F8))
                            ),
                            colors = if (selected == label) {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color(0xFF37A1F8),
                                    contentColor = Color.White
                                )
                            } else {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color(0xFF37A1F8)
                                )
                            }
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Detalles
            Text("Detalles", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = detalle.value,
                onValueChange = { detalle.value = it },
                placeholder = { Text("Tipo (vacuna, cita, medicamento)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)



            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto explicativo
            Text(
                "Nunca olvides una vacuna, cita médica o medicamento. " +
                        "Configura recordatorios personalizados para cada mascota " +
                        "y recibe alertas en el momento justo…"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones fecha y notas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { /* Acción fecha */ },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(end = 8.dp) // espacio entre botones
                        .height(40.dp),

                ) {
                    Text("Fecha y Hora", color = Color(0xFF828282))
                }
                OutlinedButton(
                    onClick = { /* Acción notas */ },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(end = 8.dp) // espacio entre botones
                        .height(40.dp),
                ) {
                    Text("Notas Especiales", color = Color(0xFF828282))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botones guardar y lista
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                Button(
                    onClick = { /* Acción guardar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF37A1F8)) ,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(2f)
                ) {
                    Text("Guardar cambios", color = Color.White)
                }
                Spacer(modifier = Modifier.width(35.dp))
                Button(
                    onClick = { /* Acción lista */ },
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