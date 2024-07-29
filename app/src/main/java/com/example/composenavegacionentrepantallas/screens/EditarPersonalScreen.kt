package com.example.composenavegacionentrepantallas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.R
import com.example.composenavegacionentrepantallas.data.OperariosProduccion

private val Gray = Color(0xFFE0E0E0)

@Composable
fun EditarPersonalScreen(navController: NavHostController) {
    var personal by remember { mutableStateOf(OperariosProduccion.operario.toMutableList()) }

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
                    text = "EDITAR\nPERSONAL",
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
                navController.navigate("anadir-operario")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar Nuevo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista desplazable del personal
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            personal.forEach { operario ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicTextField(
                        value = operario.nombre,
                        onValueChange = { newName ->
                            personal = personal.map {
                                if (it.codigo == operario.codigo) it.copy(nombre = newName) else it
                            }.toMutableList()
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    BasicTextField(
                        value = operario.codigo.toString(),
                        onValueChange = { newId ->
                            personal = personal.map {
                                if (it.codigo == operario.codigo) it.copy(codigo = newId.toIntOrNull() ?: operario.codigo) else it
                            }.toMutableList()
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = {
                        personal = personal.filter { it.codigo != operario.codigo }.toMutableList()
                    }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarPersonalScreenPreview() {
    val navController = rememberNavController()
    EditarPersonalScreen(navController = navController)
}
