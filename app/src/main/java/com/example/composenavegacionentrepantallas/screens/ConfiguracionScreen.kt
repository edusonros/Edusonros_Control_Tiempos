package com.example.composenavegacionentrepantallas.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.R

private val Gray = Color(0xFFE0E0E0)
private val defaultPassword = "edusonros"

@Composable
fun ConfiguracionScreen(navController: NavHostController) {
    var password by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf(defaultPassword) }
    var showPasswordDialog by remember { mutableStateOf(false) }
    var targetScreen by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray)
            .padding(16.dp)
    ) {
        // Encabezado
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_alot_metal),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Box(modifier = Modifier.weight(1f)) {
                Text(
                    text = "CONFIGURACIÓN",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth()
                    )


            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para editar personal
        Button(
            onClick = {
                targetScreen = "editar_personal"
                showPasswordDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Editar Personal")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para editar operaciones de producción
        Button(
            onClick = {
                targetScreen = "editar_operaciones_produccion"
                showPasswordDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Editar Operaciones de Producción")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para cambiar coordenadas
        Button(
            onClick = {
                targetScreen = "cambiar_coordenadas_fichador"
                showPasswordDialog = true
                Log.d("ConfiguracionScreen", "Botón 'Cambiar Coordenadas' presionado")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cambiar Coordenadas")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para cambiar contraseña
        Button(
            onClick = {
                targetScreen = "cambiar_contraseña"
                showPasswordDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cambiar Contraseña")
        }

        if (showPasswordDialog) {
            AlertDialog(
                onDismissRequest = { showPasswordDialog = false },
                title = { Text("Ingrese Contraseña") },
                text = {
                    Column {
                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Contraseña") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (password == currentPassword) {
                            showPasswordDialog = false
                            Log.d("ConfiguracionScreen", "Contraseña correcta. Navegando a $targetScreen")
                            when (targetScreen) {
                                "editar_personal" -> navController.navigate("editar_personal")
                                "editar_operaciones_produccion" -> navController.navigate("editar_operaciones_produccion")
                                "cambiar_coordenadas_fichador" -> navController.navigate("cambiar_coordenadas_fichador")
                                "cambiar_contraseña" -> {
                                    // Lógica para cambiar la contraseña
                                    currentPassword = password
                                    password = ""
                                    Log.d("ConfiguracionScreen", "Contraseña cambiada")
                                }
                            }
                        } else {
                            Log.d("ConfiguracionScreen", "Contraseña incorrecta")
                        }
                    }) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showPasswordDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfiguracionScreenPreview() {
    val navController = rememberNavController()
    ConfiguracionScreen(navController = navController)
}
