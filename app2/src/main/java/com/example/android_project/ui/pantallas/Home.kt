
package com.example.android_project.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_project.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}

@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomNavigationBar(navController) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            //ReminderSection()
            UsefulArticlesSection(navController)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(100.dp), // define altura total de la barra
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically // centra verticalmente
            ) {
                // Logo del perrito (izquierda)
                Image(
                    painter = painterResource(id = R.drawable.logo_face),
                    contentDescription = "Logo Pet Face",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )

                // Logo del nombre centrado (Pet Care)
                Image(
                    painter = painterResource(id = R.drawable.name),
                    contentDescription = "Nombre Pet Care",
                    modifier = Modifier
                        .size(140.dp)
                        .padding(top = 4.dp),
                    contentScale = ContentScale.Fit
                )

                // Sección del perfil
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Mi Perfil",
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .clickable {
                                navController.navigate("Perfil")
                            },
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Mi\nPerfil",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 12.sp
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        modifier = Modifier.height(115.dp)
    )
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFF4DA6FF)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    launchSingleTop = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = false
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Alimentación") },
            label = { Text("Alimentación") },
            selected = currentRoute == "alimentacion",
            onClick = {
                navController.navigate("alimentacion") {
                    launchSingleTop = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = false
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Place, contentDescription = "Rutas") },
            label = { Text("Rutas") },
            selected = currentRoute == "rutas",
            onClick = {
                navController.navigate("rutas") {
                    launchSingleTop = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = false
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Recordatorios") },
            label = { Text("Tareas") },
            selected = currentRoute == "recordatorios",
            onClick = {
                navController.navigate("recordatorios") {
                    launchSingleTop = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = false
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )
    }
}


@Composable
fun UsefulArticlesSection(navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            "Artículos útiles",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

        // 🔹 Cada artículo navega a su respectiva pantalla
        ArticleCard(imageRes = R.drawable.articulo1, onClick = { navController.navigate("articulo1") })
        ArticleCard(imageRes = R.drawable.articulo2, onClick = { navController.navigate("articulo2") })
        ArticleCard(imageRes = R.drawable.articulo3, onClick = { navController.navigate("articulo3") })
    }
}

@Composable
fun ArticleCard(imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
