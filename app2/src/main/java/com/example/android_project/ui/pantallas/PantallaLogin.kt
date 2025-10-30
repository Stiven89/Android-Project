package com.example.android_project.ui.pantallas

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
import kotlinx.coroutines.launch

@Composable
fun PantallaLogin(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "¡Qué bueno\nverte de nuevo!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 34.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Usuario
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
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
            enabled = !isLoading
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
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "¿Perdiste tu contraseña?",
            fontSize = 14.sp,
            color = Color(0xFF00A3FF),
            modifier = Modifier
                .align(Alignment.End)
                .clickable { navController.navigate("recuperar_contrasenia") }
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

        // Botón Acceder
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    isLoading = true
                    scope.launch {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                isLoading = false
                                if (task.isSuccessful) {
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                } else {
                                    errorMessage = when (task.exception?.message) {
                                        "The email address is badly formatted." -> "Formato de correo inválido"
                                        "There is no user record corresponding to this identifier. The user may have been deleted." -> "Usuario no encontrado"
                                        "The password is invalid or the user does not have a password." -> "Contraseña incorrecta"
                                        else -> "Error al iniciar sesión. Verifica tus credenciales"
                                    }
                                }
                            }
                    }
                } else {
                    errorMessage = "Por favor completa todos los campos"
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
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = "Acceder",
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

        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Crear una cuenta  ",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Inscribirse",
                fontSize = 14.sp,
                color = Color(0xFF00A3FF),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { navController.navigate("crear_cuenta") }
            )
        }
    }
}

@Composable
fun SocialLoginButton(
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