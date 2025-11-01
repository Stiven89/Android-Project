package com.example.android_project.ui.pantallas

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Lock
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilEdit(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val currentUser = auth.currentUser

    var email by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var localidad by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var nombreMascota by remember { mutableStateOf("") }
    var razaMascota by remember { mutableStateOf("") }
    var edadMascota by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(true) }
    var isSaving by remember { mutableStateOf(false) }

    // Cargar datos actuales
    LaunchedEffect(currentUser?.uid) {
        if (currentUser != null) {
            email = currentUser.email ?: ""

            firestore.collection("users")
                .document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        pin = document.getString("pin") ?: ""
                        direccion = document.getString("direccion") ?: ""
                        ciudad = document.getString("ciudad") ?: ""
                        localidad = document.getString("localidad") ?: ""
                        pais = document.getString("pais") ?: ""
                        nombreMascota = document.getString("nombreMascota") ?: ""
                        razaMascota = document.getString("razaMascota") ?: ""
                        edadMascota = document.getString("edadMascota") ?: ""
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

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Black,
        unfocusedBorderColor = Color.LightGray,
        cursorColor = Color.Black,
        disabledBorderColor = Color.LightGray,
        disabledTextColor = Color.Gray
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

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Imagen de perfil
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Correo electrónico (NO EDITABLE)
            Text("Correo electrónico", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = email,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                enabled = false,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Bloqueado",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Código PIN", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = pin,
                onValueChange = { pin = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                placeholder = { Text("Ingresa tu código PIN", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Dirección", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                placeholder = { Text("Ingresa tu dirección", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Ciudad", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = ciudad,
                onValueChange = { ciudad = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                placeholder = { Text("Ingresa tu ciudad", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Localidad", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = localidad,
                onValueChange = { localidad = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                placeholder = { Text("Ingresa tu localidad", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("País", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = pais,
                onValueChange = { pais = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                placeholder = { Text("Ingresa tu país", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Sección mascota
            Text("Mi Mascota", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Foto mascota",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("Nombre", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = nombreMascota,
                onValueChange = { nombreMascota = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                placeholder = { Text("Nombre de tu mascota", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Raza", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = razaMascota,
                onValueChange = { razaMascota = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                placeholder = { Text("Raza de tu mascota", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Edad", fontWeight = FontWeight.Medium)
            OutlinedTextField(
                value = edadMascota,
                onValueChange = { edadMascota = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColors,
                placeholder = { Text("Edad de tu mascota", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Botón guardar
            Button(
                onClick = {
                    if (currentUser != null) {
                        isSaving = true

                        val userData = hashMapOf(
                            "email" to email,
                            "pin" to pin,
                            "direccion" to direccion,
                            "ciudad" to ciudad,
                            "localidad" to localidad,
                            "pais" to pais,
                            "nombreMascota" to nombreMascota,
                            "razaMascota" to razaMascota,
                            "edadMascota" to edadMascota,
                            "updatedAt" to System.currentTimeMillis()
                        )

                        firestore.collection("users")
                            .document(currentUser.uid)
                            .set(userData)
                            .addOnSuccessListener {
                                isSaving = false
                                Toast.makeText(context, "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            }
                            .addOnFailureListener { e ->
                                isSaving = false
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                enabled = !isSaving
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Guardar", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}