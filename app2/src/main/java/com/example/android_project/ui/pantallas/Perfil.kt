package com.example.android_project.ui.pantallas

import android.widget.Toast
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_project.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class PerfilUsuario(
    val email: String = "",
    val direccion: String = "",
    val ciudad: String = "",
    val localidad: String = "",
    val pais: String = "",
    val pin: String = "",
    val nombreMascota: String = "",
    val razaMascota: String = "",
    val edadMascota: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val currentUser = auth.currentUser

    var perfil by remember { mutableStateOf<PerfilUsuario?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Cargar datos del usuario
    LaunchedEffect(currentUser?.uid) {
        if (currentUser != null) {
            firestore.collection("users")
                .document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        perfil = PerfilUsuario(
                            email = currentUser.email ?: "",
                            direccion = document.getString("direccion") ?: "",
                            ciudad = document.getString("ciudad") ?: "",
                            localidad = document.getString("localidad") ?: "",
                            pais = document.getString("pais") ?: "",
                            pin = document.getString("pin") ?: "",
                            nombreMascota = document.getString("nombreMascota") ?: "",
                            razaMascota = document.getString("razaMascota") ?: "",
                            edadMascota = document.getString("edadMascota") ?: ""
                        )
                    } else {
                        // Si no existe documento, crear uno con email
                        perfil = PerfilUsuario(email = currentUser.email ?: "")
                    }
                    isLoading = false
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                    isLoading = false
                }
        } else {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Encabezado
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
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Mi Perfil",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Imagen de perfil
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

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Datos personales
            Text("Detalles Personales", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))

            InfoField("Correo electrónico", perfil?.email)
            InfoField("Código PIN", perfil?.pin)
            InfoField("Dirección", perfil?.direccion)
            InfoField("Ciudad", perfil?.ciudad)
            InfoField("Localidad", perfil?.localidad)
            InfoField("País", perfil?.pais)

            Spacer(modifier = Modifier.height(30.dp))

            // Sección de mascota
            Text("Mi Mascota", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))

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
            InfoField("Nombre", perfil?.nombreMascota)
            InfoField("Raza", perfil?.razaMascota)
            InfoField("Edad", perfil?.edadMascota)

            Spacer(modifier = Modifier.height(30.dp))

            // Botón Editar
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
}

@Composable
fun InfoField(label: String, value: String?) {
    Column {
        Text(
            text = "$label: ${value?.takeIf { it.isNotEmpty() } ?: "Por añadir"}",
            fontSize = 16.sp,
            color = if (value.isNullOrEmpty()) Color.Gray else Color.Black,
            fontStyle = if (value.isNullOrEmpty()) androidx.compose.ui.text.font.FontStyle.Italic else androidx.compose.ui.text.font.FontStyle.Normal
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}