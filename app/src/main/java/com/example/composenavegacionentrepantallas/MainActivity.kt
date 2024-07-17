package com.example.composenavegacionentrepantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.ui.theme.ComposeNavegacionEntrePantallasTheme


private val Gray = Color(0xFFE0E0E0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavegacionEntrePantallasTheme {
                val navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }

    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_alot_metal),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = "PRODUCCION",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("pantalla01") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "Leer QRs de Producción")
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { navController.navigate("pantalla02") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "QRs de Preparacion de Maquina")
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { navController.navigate("pantalla02") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "QRs de Tiempos sin Asignar")
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { navController.navigate("pantalla02") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "Configuracion (necesita contraseña)")
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationGraphPreview() {
    NavigationGraph(rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}
/*
@Preview(showBackground = true)
@Composable
fun Pantalla01Preview() {
    Pantalla01()
}

@Preview(showBackground = true)
@Composable
fun Pantalla02Preview() {
    Pantalla02()
}*/