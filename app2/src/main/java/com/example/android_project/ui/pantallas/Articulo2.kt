package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.android_project.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack


@Composable
fun Articulo2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Barra superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp) // ajusta el tamaño a tu gusto
                    .align(Alignment.CenterStart)
            )


            Image(
                painter = painterResource(id = R.drawable.dog_logo),
                contentDescription = "Logo perrito",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterEnd)
            )
        }

        // Contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Card con imagen en todo el espacio
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White // Fondo blanco para que no tape la imagen
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.articulo2), // <-- Reemplaza con tu imagen
                    contentDescription = "Imagen de higiene",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Rutinas de ejercicio y juego recomendados para tu mascota ",
                fontSize = 28.sp,
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

