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
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.filled.Check


@Composable
fun Articulo3(navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }

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
                        .height(180.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
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


                Text(
                    text = "HIGIENE BÁSICA Y CUIDADO DEL PELAJE",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,         // negrita
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Mantener la higiene de tu mascota no solo mejora su aspecto, sino también su salud. Un pelaje limpio y cuidado ayuda a prevenir enfermedades cutáneas y promueve una convivencia más sana en casa.",
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Justify,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                // ===== CONSEJOS CLAVE (NEGRITA) =====
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "CONSEJOS CLAVE",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,         // negrita
                            color = Color.Black
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

                SeccionArticulo(
                    titulo = "🛁 Baño y limpieza",
                    contenido = "Usa productos específicos para mascotas y agua tibia. Los baños regulares eliminan suciedad y parásitos, y fortalecen el vínculo entre tú y tu mascota."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticulo(
                    titulo = "✂️ Cepillado del pelaje",
                    contenido = "Cepillar el pelaje mantiene la piel oxigenada y libre de enredos. Adapta la frecuencia según la longitud del pelo."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticulo(
                    titulo = "👂 Cuidado de oídos y ojos",
                    contenido = "Mantén los oídos y ojos limpios con productos adecuados. Revisa si hay enrojecimiento o mal olor, y consulta al veterinario si notas algo inusual."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticulo(
                    titulo = "💅 Corte de uñas",
                    contenido = "Unas uñas largas pueden incomodar al caminar. Córtalas con cuidado cada pocas semanas, o pide ayuda a un profesional."
                )

                Spacer(modifier = Modifier.height(16.dp))

                SeccionArticulo(
                    titulo = "🦷 Higiene dental",
                    contenido = "Usa pasta dental especial para mascotas y cepilla sus dientes regularmente. La prevención evita el mal aliento y enfermedades bucales."
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
                            text = "Cada mascota es diferente. Consulta con tu veterinario para definir una rutina de higiene personalizada.",
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
                    fontWeight = FontWeight.Bold,         // negrita
                    color = Color.Black             // azul
                )

                Spacer(modifier = Modifier.height(16.dp))

                ArticuloRelacionado("Nutrición canina básica") {
                    navController.navigate("articulo1")
                }

                Spacer(modifier = Modifier.height(12.dp))

                ArticuloRelacionado("Ejercicio y actividad física") {
                    navController.navigate("articulo2")
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
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
fun SeccionArticulo(titulo: String, contenido: String) {
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
fun ArticuloRelacionado(titulo: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("📄", fontSize = 24.sp, modifier = Modifier.padding(end = 12.dp))
            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF0D47A1),
                modifier = Modifier.weight(1f)
            )
            Text("→", fontSize = 20.sp, color = Color(0xFF1976D2))
        }
    }
}
