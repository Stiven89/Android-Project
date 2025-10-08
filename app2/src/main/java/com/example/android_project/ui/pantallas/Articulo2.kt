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
fun Articulo2(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // 🔹 Barra superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Artículo 2",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            // 🔹 Botón atrás funcional
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.Black)
            }

            // 🔹 Logo lateral derecho
            Image(
                painter = painterResource(id = R.drawable.logo_petcare),
                contentDescription = "Logo PetCare",
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
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
                    painter = painterResource(id = R.drawable.articulo2),
                    contentDescription = "Ejercicio mascota",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Rutinas de ejercicio y juego recomendadas para tu mascota",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Las rutinas de ejercicio y juego son fundamentales para el bienestar físico y emocional de tu mascota. Realizar caminatas diarias, juegos con pelotas o actividades de agilidad no solo ayudan a mantener un peso saludable y fortalecer sus músculos, sino que también estimulan su mente y reducen el estrés. Dedicar al menos 30 minutos al día a estas actividades mejora su calidad de vida y evita problemas de comportamiento causados por el aburrimiento. Además, compartir estos momentos fortalece el vínculo afectivo entre tú y tu compañero de cuatro patas, convirtiéndolos en experiencias divertidas y llenas de cariño.",
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp
            )
        }
    }
}
