package com.example.android_project.ui.pantallas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_project.R

@Composable
fun Articulo2(navController: NavController) {
    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F9FF))
    ) {
        // 🔹 Barra superior con botón atrás, favorito y logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFF1976D2)
                )
            }

            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = { isFavorite = !isFavorite }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = if (isFavorite) Color(0xFF1976D2) else Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.logo_petcare),
                    contentDescription = "Logo PetCare",
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        // 🔹 Contenido scrollable
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Imagen principal (mismo tamaño que Artículo 1)
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

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "RUTINAS DE EJERCICIO Y JUEGO RECOMENDADAS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "El ejercicio regular es esencial para mantener el bienestar físico y emocional de tu mascota. Actividades como caminatas, juegos de pelota o ejercicios de agilidad ayudan a mantener su peso ideal, fortalecer los músculos y prevenir el estrés o la ansiedad. Dedicar al menos 30 minutos diarios a la actividad física mejora su calidad de vida y fortalece el vínculo contigo.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Justify,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 🔹 Consejos clave
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "CONSEJOS CLAVE",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    TipItemEjercicio("Asegúrate de que tu mascota haga ejercicio al menos 30 minutos al día.")
                    TipItemEjercicio("Combina caminatas, juegos interactivos y entrenamiento mental.")
                    TipItemEjercicio("Evita ejercitar a tu mascota en horas de mucho calor.")
                    TipItemEjercicio("Ofrece siempre agua fresca después de la actividad.")
                    TipItemEjercicio("Consulta al veterinario antes de cambiar su rutina física.")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 🔹 Sección de artículos relacionados
            Text(
                text = "ARTÍCULOS RELACIONADOS",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            ArticuloRelacionado(
                titulo = "Beneficios del juego para perros - Upendi",
                url = "https://upendiclinicaveterinaria.com/juguetes-interactivos-y-los-beneficios-en-la-salud-de-tu-perro/"
            )

            Spacer(modifier = Modifier.height(12.dp))

            ArticuloRelacionado(
                titulo = "Ejercicio y salud en gatos - BlueCross",
                url = "https://www.bluecross.org.uk/advice/cat/cat-exercise"
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun TipItemEjercicio(texto: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            tint = Color(0xFF1565C0),
            modifier = Modifier
                .size(20.dp)
                .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = texto,
            fontSize = 13.sp,
            color = Color(0xFF0D47A1),
            lineHeight = 18.sp
        )
    }
}

@Composable
fun ArticuloRelacionado(titulo: String, url: String) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("🔗", fontSize = 22.sp, modifier = Modifier.padding(end = 12.dp))
            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF0D47A1),
                modifier = Modifier.weight(1f)
            )
            Text("→", fontSize = 18.sp, color = Color(0xFF1976D2))
        }
    }
}
