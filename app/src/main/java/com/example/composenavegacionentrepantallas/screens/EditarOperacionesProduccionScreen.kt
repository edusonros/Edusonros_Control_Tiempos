package com.example.composenavegacionentrepantallas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.data.OperacionProduccion
import com.example.composenavegacionentrepantallas.data.OperacionesProduccion
import com.example.composenavegacionentrepantallas.R

private val Gray = Color(0xFFE0E0E0)

@Composable
fun EditarOperacionesProduccionScreen(navController: NavHostController) {
    var operaciones = remember { mutableStateListOf(*OperacionesProduccion.operaciones.toTypedArray()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray)
            .padding(16.dp)
    ) {
        // Encabezado
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_alot_metal),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Box(modifier = Modifier.weight(1f)) {
                Text(
                    text = "EDITAR\nOPERACIONES",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón "Ingresar Nuevo"
        Button(
            onClick = {
                navController.navigate("anadir-operacion")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar Nueva Operacion")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Encabezado de las columnas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Código", modifier = Modifier.width(80.dp))
            Text("Nombre", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Lista desplazable
        LazyColumn {
            itemsIndexed(operaciones) { index, operacion ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Campo para el código
                    TextField(
                        value = operacion.codigo.toString(),
                        onValueChange = { newCode ->
                            val newCodeInt = newCode.toIntOrNull() ?: operacion.codigo
                            operaciones[index] = operacion.copy(codigo = newCodeInt)
                        },
                        modifier = Modifier.width(80.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Campo para el nombre
                    TextField(
                        value = operacion.nombre,
                        onValueChange = { newName ->
                            operaciones[index] = operacion.copy(nombre = newName)
                        },
                        modifier = Modifier.weight(1f).heightIn(min = 56.dp),
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Botón para eliminar la operación
                    Button(onClick = { operaciones.removeAt(index) }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarOperacionesProduccionScreenPreview() {
    val navController = rememberNavController()
    EditarOperacionesProduccionScreen(navController = navController)
}
