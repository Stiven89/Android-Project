package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_project.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavController) {

    // Datos fijos simulados (en un caso real vendrían del ViewModel)
    val email = "ejemplo@correo.com"
    val direccion = "Calle 123 #45-67"
    val ciudad = "Bogotá"
    val localidad = "Puente Aranda"
    val pais = "Colombia"
    val pin = "110111"
    val nombreMascota = "Firulais"
    val razaMascota = "Labrador"
    val edadMascota = "3 años"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // ---- Encabezado ----
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Atrás",
                tint = Color.Black,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Mi Perfil",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---- Imagen de perfil ----
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---- Datos personales ----
        Text("Detalles Personales", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))

        Text("Correo electrónico: $email")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Código PIN: $pin")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Dirección: $direccion")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ciudad: $ciudad")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Localidad: $localidad")
        Spacer(modifier = Modifier.height(8.dp))
        Text("País: $pais")

        Spacer(modifier = Modifier.height(30.dp))

        // ---- Sección de mascota ----
        Text("Mi Mascota", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))

        // Imagen del perrito
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Foto de la mascota",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Nombre: $nombreMascota")
        Text("Raza: $razaMascota")
        Text("Edad: $edadMascota")

        Spacer(modifier = Modifier.height(30.dp))

        // ---- Botón Editar ----
        Button(
            onClick = { navController.navigate("perfil_edit") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Editar", color = Color.White, fontSize = 16.sp)
        }
    }
}
