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
fun PerfilEdit(navController: NavController) {

    var email by remember { mutableStateOf("ejemplo@correo.com") }
    var pin by remember { mutableStateOf("110111") }
    var direccion by remember { mutableStateOf("Calle 123 #45-67") }
    var ciudad by remember { mutableStateOf("Bogotá") }
    var localidad by remember { mutableStateOf("Puente Aranda") }
    var pais by remember { mutableStateOf("Colombia") }
    var nombreMascota by remember { mutableStateOf("Firulais") }
    var razaMascota by remember { mutableStateOf("Labrador") }
    var edadMascota by remember { mutableStateOf("3 años") }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Black,
        unfocusedBorderColor = Color.LightGray,
        cursorColor = Color.Black
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Encabezado
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Atrás",
                tint = Color.Black,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Editar Perfil", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Imagen de perfil
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Campos personales
        Text("Correo electrónico")
        OutlinedTextField(value = email, onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(12.dp))
        Text("Código PIN")
        OutlinedTextField(value = pin, onValueChange = { pin = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(12.dp))
        Text("Dirección")
        OutlinedTextField(value = direccion, onValueChange = { direccion = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(12.dp))
        Text("Ciudad")
        OutlinedTextField(value = ciudad, onValueChange = { ciudad = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(12.dp))
        Text("Localidad")
        OutlinedTextField(value = localidad, onValueChange = { localidad = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(12.dp))
        Text("País")
        OutlinedTextField(value = pais, onValueChange = { pais = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(20.dp))

        // Imagen del perrito
        Text("Mi Mascota", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Foto mascota",
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Nombre")
        OutlinedTextField(value = nombreMascota, onValueChange = { nombreMascota = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(12.dp))
        Text("Raza")
        OutlinedTextField(value = razaMascota, onValueChange = { razaMascota = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(12.dp))
        Text("Edad")
        OutlinedTextField(value = edadMascota, onValueChange = { edadMascota = it },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), colors = textFieldColors)

        Spacer(modifier = Modifier.height(30.dp))

        // Botón guardar
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Guardar", color = Color.White, fontSize = 16.sp)
        }
    }
}
