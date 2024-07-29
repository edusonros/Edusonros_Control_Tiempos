package com.example.composenavegacionentrepantallas.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.data.OperariosProduccion
import com.example.composenavegacionentrepantallas.data.OperarioProduccion

@Composable
fun IngresarOperarioScreen(navController: NavHostController) {
    var nombreOperario by remember { mutableStateOf("") }
    var codigoOperario by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    fun agregarOperario() {
        if (nombreOperario.isNotEmpty() && codigoOperario.isNotEmpty()) {
            try {
                val codigo = codigoOperario.toInt()
                val nuevoOperario = OperarioProduccion(nombreOperario, codigo)
                OperariosProduccion.operario.add(nuevoOperario)
                dialogMessage = "Operario agregado con éxito"
                nombreOperario = ""
                codigoOperario = ""
            } catch (e: NumberFormatException) {
                dialogMessage = "El código del operario debe ser un número válido"
            }
        } else {
            dialogMessage = "Todos los campos son obligatorios"
        }
        showDialog = true
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Resultado") },
            text = { Text(text = dialogMessage) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Ingresar Nuevo Operario", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombreOperario,
            onValueChange = { nombreOperario = it },
            label = { Text("Nombre del Operario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = codigoOperario,
            onValueChange = { codigoOperario = it },
            label = { Text("Código del Operario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { agregarOperario() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Agregar Operario")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngresarOperarioScreenPreview() {
    val navController = rememberNavController()
    IngresarOperarioScreen(navController = navController)
}
