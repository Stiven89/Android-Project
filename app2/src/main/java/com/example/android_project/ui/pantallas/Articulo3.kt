package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.filled.Check

@Composable
fun Articulo3(navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            // Header con botones
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
                        tint = Color.Black,
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
                            tint = if (isFavorite) Color.Red else Color.Gray,
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

            // Contenido scrolleable
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Imagen principal
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.articulo3),
                        contentDescription = "Imagen de higiene",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Tiempo de lectura
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE3F2FD)
                        )
                    ) {
                        Text(
                            text = "⏱️ 5 min de lectura",
                            fontSize = 12.sp,
                            color = Color(0xFF1976D2),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Título
                Text(
                    text = "Higiene básica y cuidado del pelaje",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Introducción
                Text(
                    text = "La higiene básica de una mascota es fundamental para mantener su salud y bienestar. Incluye el baño periódico según su tipo de pelaje, el cepillado regular para evitar enredos y eliminar el pelo muerto, la limpieza de oídos y ojos para prevenir infecciones, el corte de uñas para evitar molestias al caminar y la higiene bucal para cuidar dientes y encías.",
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Justify,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Sección de tips destacados
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF3E0)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "💡 Consejos clave",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE65100)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        TipItem("Baña a tu mascota cada 2-4 semanas")
                        TipItem("Cepilla el pelaje 2-3 veces por semana")
                        TipItem("Limpia oídos y ojos semanalmente")
                        TipItem("Corta las uñas cada 3-4 semanas")
                        TipItem("Cepilla los dientes 2-3 veces por semana")
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Secciones detalladas
                SeccionArticulo(
                    titulo = "🛁 Baño y limpieza",
                    contenido = "La frecuencia del baño depende del tipo de pelaje y estilo de vida de tu mascota. Los perros generalmente necesitan baños cada 2-4 semanas, mientras que los gatos se asean solos. Usa productos específicos para mascotas y agua tibia."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticulo(
                    titulo = "✂️ Cepillado del pelaje",
                    contenido = "El cepillado regular previene nudos, elimina pelo muerto y distribuye los aceites naturales. Las razas de pelo largo requieren cepillado diario, mientras que las de pelo corto pueden cepillarse 2-3 veces por semana."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticulo(
                    titulo = "👂 Cuidado de oídos y ojos",
                    contenido = "Revisa semanalmente los oídos en busca de enrojecimiento, mal olor o secreciones. Limpia suavemente con productos veterinarios. Los ojos deben estar limpios; retira cualquier secreción con una gasa húmeda."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticulo(
                    titulo = "💅 Corte de uñas",
                    contenido = "Las uñas largas pueden causar dolor y problemas al caminar. Córtalas cada 3-4 semanas usando cortauñas específicos. Ten cuidado de no cortar la parte viva de la uña que contiene vasos sanguíneos."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticulo(
                    titulo = "🦷 Higiene dental",
                    contenido = "La salud bucal es crucial. Cepilla los dientes de tu mascota 2-3 veces por semana con pasta dental específica para animales. Los juguetes dentales y las revisiones veterinarias regulares también son importantes."
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Nota importante
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E9)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "ℹ️",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        Text(
                            text = "Recuerda: Cada mascota es única. Consulta con tu veterinario para establecer una rutina de higiene personalizada según las necesidades específicas de tu compañero.",
                            fontSize = 13.sp,
                            color = Color(0xFF2E7D32),
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Artículos relacionados
                Text(
                    text = "Artículos relacionados",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                ArticuloRelacionado(
                    titulo = "Nutrición canina básica",
                    onClick = { navController.navigate("articulo1") }
                )

                Spacer(modifier = Modifier.height(12.dp))

                ArticuloRelacionado(
                    titulo = "Ejercicio y actividad física",
                    onClick = { navController.navigate("articulo2") }
                )

                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        // Botón flotante de compartir
        FloatingActionButton(
            onClick = { /* Implementar compartir */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = Color(0xFF00A3FF),
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "Compartir artículo"
            )
        }
    }
}

@Composable
fun TipItem(texto: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            tint = Color(0xFF4CAF50),
            modifier = Modifier
                .size(20.dp)
                .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = texto,
            fontSize = 13.sp,
            color = Color(0xFF5D4037),
            lineHeight = 18.sp
        )
    }
}

@Composable
fun SeccionArticulo(titulo: String, contenido: String) {
    Column {
        Text(
            text = titulo,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
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
fun ArticuloRelacionado(titulo: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "📄",
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "→",
                fontSize = 20.sp,
                color = Color(0xFF00A3FF)
            )
        }
    }
}