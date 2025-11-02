package com.example.android_project.ui.pantallas

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.android_project.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun PantallaLogin(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    // Obtener Web Client ID desde resources
    val webClientId = context.getString(R.string.default_web_client_id)

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                isLoading = true
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { signInTask ->
                        isLoading = false
                        if (signInTask.isSuccessful) {
                            Log.d("PetCare", "Google Sign-In exitoso")
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            errorMessage = "Error al iniciar sesión con Google"
                            Log.e("PetCare", "Error Google Sign-In", signInTask.exception)
                        }
                    }
            } catch (e: ApiException) {
                errorMessage = "Error de Google Sign-In"
                Log.e("PetCare", "Google Sign-In falló", e)
            }
        }
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
            label = { Text("Correo electrónico") },
            placeholder = { Text("Correo electrónico") },
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
        SocialLoginButton(
            backgroundColor = Color.White,
            borderColor = Color(0xFFE0E0E0),
            onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(webClientId)
                    .requestEmail()
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                // Cerrar sesión previa para forzar selector de cuentas
                googleSignInClient.signOut().addOnCompleteListener {
                    googleSignInLauncher.launch(googleSignInClient.signInIntent)
                }
            }
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