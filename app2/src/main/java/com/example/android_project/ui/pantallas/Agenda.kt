package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_project.R

@Composable
fun Agenda() {
    var selectedDate by remember { mutableStateOf("Vie 26") }
    val recordatorios = listOf("Oferta del día", "Comprar concentrado")
    val completados = listOf("Tiempo de ejercicio diario", "Cepillar su pelaje", "Baño y limpieza")

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hoy",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )

                // Botón atrás
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
                        .size(48.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd)
                        .padding(end = 12.dp)
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
        ) {
            // calendario temporal
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Jue 25", "Vie 26", "Sáb 27", "Dom 28", "Lun 29").forEach { date ->
                    val isSelected = date == selectedDate
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 12.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                            .background(if (isSelected) Color(0x4D37A1F8) else Color.Transparent)
                            .weight(1f)
                    ) {
                        Text(
                            text = date.take(3).uppercase(),
                            fontSize = 12.sp,
                            color = if (isSelected) Color.Black else Color.Gray
                        )
                        Text(
                            text = date.takeLast(2),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.Black else Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sección recordatorios
            Text(
                text = "RECORDATORIOS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37A1F8),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            recordatorios.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0x4D37A1F8))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = false, onCheckedChange = { })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(item, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección completados
            Text(
                text = "COMPLETADOS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37A1F8),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            completados.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .background(Color(0xFFF5F5F5))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = true,
                        onCheckedChange = { },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF9F9F9F),
                            checkmarkColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(item, fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Bold,)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón añadir
            Button(
                onClick = { /* Acción añadir */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Añadir", color = Color.White)
            }
        }
    }

}