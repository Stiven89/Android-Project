package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var localidad by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var nombreMascota by remember { mutableStateOf("") }
    var razaMascota by remember { mutableStateOf("") }
    var edadMascota by remember { mutableStateOf("") }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Black,
        unfocusedBorderColor = Color.LightGray,
        cursorColor = Color.Black,
        focusedLabelColor = Color.Black,
        unfocusedLabelColor = Color.Gray
    )

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
                        navController.popBackStack() // 🔙 Regresa a la pantalla anterior
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
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---- Detalles personales ----
        Text("Detalles Personales", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        Text("Correo electrónico")
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("ejemplo@correo.com") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text("Contraseña")
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("********") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )

        TextButton(onClick = { /* Acción cambiar contraseña */ }) {
            Text("Cambiar Contraseña")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---- Dirección ----
        Text("Detalles de la Dirección", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        Text("Código PIN")
        OutlinedTextField(
            value = pin,
            onValueChange = { pin = it },
            placeholder = { Text("0000") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text("Dirección")
        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            placeholder = { Text("Calle 123 #45-67") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text("Ciudad")
        OutlinedTextField(
            value = ciudad,
            onValueChange = { ciudad = it },
            placeholder = { Text("Bogotá") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text("Localidad")
        OutlinedTextField(
            value = localidad,
            onValueChange = { localidad = it },
            placeholder = { Text("Puente Aranda") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("País")
        OutlinedTextField(
            value = pais,
            onValueChange = { pais = it },
            placeholder = { Text("Colombia") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ---- Mascota ----
        Text("Mi Mascota", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        Text("Nombre de la mascota")
        OutlinedTextField(
            value = nombreMascota,
            onValueChange = { nombreMascota = it },
            placeholder = { Text("Firulais") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text("Raza")
        OutlinedTextField(
            value = razaMascota,
            onValueChange = { razaMascota = it },
            placeholder = { Text("Labrador") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text("Edad")
        OutlinedTextField(
            value = edadMascota,
            onValueChange = { edadMascota = it },
            placeholder = { Text("3 años") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(30.dp))

        // ---- Botón guardar ----
        Button(
            onClick = { /* Acción guardar perfil */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Guardar", color = Color.White, fontSize = 16.sp)
        }
    }
}
