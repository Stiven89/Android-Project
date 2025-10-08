package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_project.R

@Composable
fun Articulo1(navController: NavController) {   // ✅ Recibe el NavController
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Artículo 1",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )


            IconButton(
                onClick = { navController.popBackStack() },  // ✅ Acción corregida
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
            }


            Image(
                painter = painterResource(id = R.drawable.logo_petcare),
                contentDescription = "Logo PetCare",
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.articulo1),
                    contentDescription = "Imagen de higiene",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "¿Cómo subirle las defensas a tu perro?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Para fortalecer el sistema inmunológico de tu perro, es fundamental ofrecerle una alimentación balanceada rica en nutrientes, mantener sus vacunas al día y fomentar el ejercicio regular. Actividades como paseos, juegos y estimulación mental ayudan a reducir el estrés, que puede debilitar sus defensas. Además, visitas periódicas al veterinario permiten detectar y prevenir enfermedades a tiempo, asegurando que tu mascota esté protegida y saludable.",
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp
            )
        }
    }
}
