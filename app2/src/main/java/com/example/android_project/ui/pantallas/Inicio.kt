package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_project.R

@Composable
fun Inicio(navController: NavController) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Logo y título principal
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_petcare),
                    contentDescription = "Logo Pet Care",
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Pet Care",
                    color = Color(0xFF2196F3),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 35.sp)
                )
            }

            // Botones inferiores
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = { navController.navigate("login") }) {
                    Text(
                        text = "Acceder",
                        color = Color(0xFF2196F3),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("home") }) {
                    Text(
                        text = "Home",
                        color = Color(0xFF2196F3),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp)
                    )
                }
            }
        }
    }
}
