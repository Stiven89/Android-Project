package com.example.android_project.ui.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android_project.R

@Composable
fun PantallaLogin(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Nombre de usuario o correo electrónico") },
            placeholder = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color(0xFF00A3FF)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            placeholder = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Color(0xFF00A3FF)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "¿Perdiste tu contraseña?",
            fontSize = 14.sp,
            color = Color(0xFF00A3FF),
            modifier = Modifier
                .align(Alignment.End)
                .clickable { navController.navigate("forgot_password") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // BOTÓN TEMPORAL PARA IR A HIGIENE
        Button(
            onClick = { navController.navigate("hygiene") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFDB44B)
            )
        ) {
            Text(
                text = "Ver Higiene (TEMPORAL)",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00A3FF)
            )
        ) {
            Text(
                text = "Acceder",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "- O Continuar con -",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón social login (Google como ejemplo)
        SocialLoginButton(
            backgroundColor = Color.White,
            borderColor = Color(0xFFE0E0E0),
            onClick = { }
        ) {
            Text(
                "G",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4285F4)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
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
                modifier = Modifier.clickable { navController.navigate("register") }
            )
        }
    }
}

@Composable
fun SocialLoginButton(
    backgroundColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor
        ),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        content()
    }
}
