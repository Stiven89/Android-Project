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
fun Articulo1(navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F9FF))
        ) {
            // Encabezado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color(0xFF1976D2),
                        modifier = Modifier.size(32.dp)
                    )
                }

                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
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
                        painter = painterResource(id = R.drawable.dog_logo),
                        contentDescription = "Logo perrito",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            // Contenido
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(175.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.articulo1),
                        contentDescription = "Imagen de nutrición",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                    ) {
                        Text(
                            text = "⏱️ 4 min de lectura",
                            fontSize = 12.sp,
                            color = Color(0xFF1976D2),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "NUTRICIÓN BÁSICA Y ALIMENTACIÓN SALUDABLE",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Una alimentación equilibrada es clave para mantener a tu mascota sana y llena de energía. Proporcionar los nutrientes adecuados ayuda a fortalecer su sistema inmunológico, mantener su peso ideal y mejorar su calidad de vida.",
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Justify,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

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

                        TipItemA("Elige un alimento balanceado de buena calidad")
                        TipItemA("Proporciona agua limpia y fresca a diario")
                        TipItemA("Evita dar sobras o alimentos procesados")
                        TipItemA("Consulta al veterinario sobre la cantidad ideal de comida")
                        TipItemA("Mantén horarios regulares de alimentación")
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                SeccionArticuloA(
                    titulo = "🥩 Proteínas y energía",
                    contenido = "Las proteínas son esenciales para el desarrollo muscular y el mantenimiento del organismo. Asegúrate de que su alimento principal las contenga en buen porcentaje."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticuloA(
                    titulo = "🥦 Frutas y verduras",
                    contenido = "Incluir pequeñas porciones de frutas y verduras puede aportar fibra y vitaminas importantes. Evita las tóxicas como la cebolla o el chocolate."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticuloA(
                    titulo = "💧 Hidratación constante",
                    contenido = "La hidratación es fundamental. Cambia el agua del bebedero varias veces al día, especialmente en climas cálidos."
                )

                Spacer(modifier = Modifier.height(30.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("ℹ️", fontSize = 24.sp, modifier = Modifier.padding(end = 12.dp))
                        Text(
                            text = "Cada mascota tiene necesidades únicas. Consulta con tu veterinario antes de hacer cambios importantes en su dieta.",
                            fontSize = 13.sp,
                            color = Color(0xFF0D47A1),
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "ARTÍCULOS RELACIONADOS",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(16.dp))

                ArticuloRelacionadoA(
                    titulo = "Alimentos saludables para perros - Bonza",
                    url = "https://www.bonza.dog/es/2024/06/investigacion-sobre-alimentos-funcionales-para-la-salud-canina/"
                )

                Spacer(modifier = Modifier.height(12.dp))

                ArticuloRelacionadoA(
                    titulo = "Guía nutricional para gatos - Bellaandduke",
                    url = "https://www.bellaandduke.com/raw-feeding/our-complete-guide-to-cat-and-kitten-feeding-with-charts-weights-and-schedules/"
                )

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun TipItemA(texto: String) {
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
            modifier = Modifier.size(20.dp).padding(top = 2.dp)
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
fun SeccionArticuloA(titulo: String, contenido: String) {
    Column {
        Text(
            text = titulo,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = contenido,
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Justify,
            lineHeight = 22.sp
        )
    }
}

@Composable
fun ArticuloRelacionadoA(titulo: String, url: String) {
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
