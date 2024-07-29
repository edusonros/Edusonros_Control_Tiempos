package com.example.composenavegacionentrepantallas.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.data.OperacionesProduccion
import com.example.composenavegacionentrepantallas.data.OperacionProduccion

@Composable
fun IngresarOperacionScreen(navController: NavHostController) {
    var nombreOperacion by remember { mutableStateOf("") }
    var codigoOperacion by remember { mutableStateOf("") }
    var valorOperacion by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    fun agregarOperacion() {
        if (nombreOperacion.isNotEmpty() && codigoOperacion.isNotEmpty() && valorOperacion.isNotEmpty()) {
            try {
                val codigo = codigoOperacion.toInt()
                val valor = valorOperacion.toInt()
                val nuevaOperacion = OperacionProduccion(nombreOperacion, codigo, valor)
                OperacionesProduccion.operaciones.add(nuevaOperacion)
                dialogMessage = "Operación agregada con éxito"
                nombreOperacion = ""
                codigoOperacion = ""
                valorOperacion = ""
            } catch (e: NumberFormatException) {
                dialogMessage = "El código y el valor de la operación deben ser números válidos"
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
        Text("Ingresar Nueva Operación", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombreOperacion,
            onValueChange = { nombreOperacion = it },
            label = { Text("Nombre de la Operación") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = codigoOperacion,
            onValueChange = { codigoOperacion = it },
            label = { Text("Código de la Operación") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = valorOperacion,
            onValueChange = { valorOperacion = it },
            label = { Text("Valor de la Operación") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { agregarOperacion() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Agregar Operación")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngresarOperacionScreenPreview() {
    val navController = rememberNavController()
    IngresarOperacionScreen(navController = navController)
}
