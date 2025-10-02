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
fun Articulo3() {
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
                    .size(32.dp)
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

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Higiene basica y cuidado del pelaje ",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "La higiene básica de una mascota es fundamental para mantener su salud y bienestar, e incluye aspectos como el baño periódico según su tipo de pelaje y especie, el cepillado regular para evitar enredos y eliminar el pelo muerto, la limpieza de oídos y ojos para prevenir infecciones, el corte de uñas para evitar molestias al caminar, y la higiene bucal para cuidar sus dientes y encías. Además, es importante mantener limpia su cama, juguetes y el lugar donde come, así como proporcionarle agua fresca y alimentos en recipientes lavados diariamente",
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp
            )
        }
    }
}