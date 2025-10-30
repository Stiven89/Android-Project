package com.example.android_project.ui.pantallas

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.android_project.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Crear_cuenta(navController: NavHostController? = null) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    // Diálogo de éxito
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = {
                showSuccessDialog = false
                navController?.navigate("login") {
                    popUpTo("crear_cuenta") { inclusive = true }
                }
            },
            title = { Text("¡Cuenta creada!") },
            text = { Text("Tu cuenta ha sido creada exitosamente. Ahora puedes iniciar sesión.") },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        navController?.navigate("login") {
                            popUpTo("crear_cuenta") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00A3FF)
                    )
                ) {
                    Text("Iniciar sesión")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Crear una cuenta",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Usuario
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it.trim()
                errorMessage = ""
            },
            label = { Text("Nombre de usuario") },
            placeholder = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Usuario",
                    tint = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color.Gray
            ),
            enabled = !isLoading,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorMessage = ""
            },
            label = { Text("Contraseña") },
            placeholder = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Contraseña",
                    tint = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color.Gray
            ),
            enabled = !isLoading,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirmar contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                errorMessage = ""
            },
            label = { Text("Confirmar contraseña") },
            placeholder = { Text("Vuelve a escribir la contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Confirmar contraseña",
                    tint = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color.Gray
            ),
            enabled = !isLoading,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Términos y condiciones
        Text(
            text = "Al hacer clic en el botón «Registrarse»,\nusted acepta términos y condiciones.",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        // Mostrar mensaje de error si existe
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = errorMessage,
                fontSize = 14.sp,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de registrar
        Button(
            onClick = {
                // Validaciones
                when {
                    username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                        errorMessage = "Por favor completa todos los campos"
                    }
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches() -> {
                        errorMessage = "Por favor ingresa un correo electrónico válido"
                    }
                    password.length < 6 -> {
                        errorMessage = "La contraseña debe tener al menos 6 caracteres"
                    }
                    password != confirmPassword -> {
                        errorMessage = "Las contraseñas no coinciden"
                    }
                    else -> {
                        isLoading = true
                        errorMessage = ""
                        Log.d("PetCare", "Intentando crear cuenta con: $username")

                        auth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener { task ->
                                isLoading = false

                                if (task.isSuccessful) {
                                    Log.d("PetCare", "✅ Usuario creado exitosamente")
                                    showSuccessDialog = true

                                    // Opcional: Guardar en Firestore
                                    val userId = task.result?.user?.uid
                                    if (userId != null) {
                                        val userData = hashMapOf(
                                            "email" to username,
                                            "createdAt" to System.currentTimeMillis()
                                        )

                                        firestore.collection("users")
                                            .document(userId)
                                            .set(userData)
                                            .addOnSuccessListener {
                                                Log.d("PetCare", "✅ Datos guardados en Firestore")
                                            }
                                            .addOnFailureListener { e ->
                                                Log.e("PetCare", "⚠️ Error Firestore (no crítico)", e)
                                            }
                                    }
                                } else {
                                    Log.e("PetCare", "❌ Error al crear cuenta", task.exception)

                                    errorMessage = when {
                                        task.exception?.message?.contains("email address is already in use") == true ->
                                            "Este correo ya está registrado"
                                        task.exception?.message?.contains("email address is badly formatted") == true ->
                                            "Formato de correo inválido"
                                        task.exception?.message?.contains("network") == true ->
                                            "Error de conexión. Verifica tu internet"
                                        task.exception?.message?.contains("WEAK_PASSWORD") == true ->
                                            "La contraseña es muy débil"
                                        else ->
                                            "Error: ${task.exception?.message ?: "Intenta de nuevo"}"
                                    }
                                }
                            }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00A3FF)
            ),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Crear una cuenta",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "- O continuar con -",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón social (Google)
        SocialButton(
            backgroundColor = Color.White,
            borderColor = Color(0xFFE0E0E0),
            onClick = { /* Acción con Google */ }
        ) {
            Image(
                painter = painterResource(R.drawable.google_logo),
                contentDescription = "Google logo",
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Texto de ya tengo cuenta
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ya tengo una cuenta  ",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Acceder",
                fontSize = 14.sp,
                color = Color(0xFF00A3FF),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    navController?.navigate("login") {
                        popUpTo("crear_cuenta") { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun SocialButton(
    backgroundColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(56.dp)
            .clickable { onClick() },
        shape = CircleShape,
        color = backgroundColor,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}