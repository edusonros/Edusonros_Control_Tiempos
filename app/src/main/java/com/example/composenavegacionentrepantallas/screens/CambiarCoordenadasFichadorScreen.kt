package com.example.composenavegacionentrepantallas.screens

// CambiarCoordenadasFichadorScreen.kt
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.data.Coordinates
import com.example.composenavegacionentrepantallas.data.CoordenadasFichadorALOT

@Composable
fun CambiarCoordenadasFichadorScreen(navController: NavHostController) {
    Log.d("CambiarCoordenadasFichadorScreen", "Entrando en CambiarCoordenadasFichadorScreen")

    var coordenadas by remember { mutableStateOf(CoordenadasFichadorALOT.coordenadas.toList()) }
    var nombre by remember { mutableStateOf("") }
    var latitud by remember { mutableStateOf("") }
    var longitud by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier =Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Cambiar Coordenadas", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = latitud,
            onValueChange = { latitud = it },
            label = { Text("Latitud") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = longitud,
            onValueChange = { longitud = it },
            label = { Text("Longitud") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.isNotEmpty() && latitud.isNotEmpty() && longitud.isNotEmpty()) {
                try {
                    val newCoordenada = Coordinates(nombre, latitud.toDouble(), longitud.toDouble())
                    coordenadas = coordenadas + newCoordenada
                    CoordenadasFichadorALOT.coordenadas.add(newCoordenada)
                    nombre = ""
                    latitud = ""
                    longitud = ""
                }catch (e: NumberFormatException) {
                // Manejar el error de formato de número
                    Toast.makeText(context, "Latitud y Longitud deben ser números válidos", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text("Agregar y Guardar Coordenadas")
            }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(coordenadas) { coord ->
                Row(modifier =Modifier
                    .fillMaxWidth()
                    .padding(vertical=8.dp)) {
                    Text("${coord.nombre}: ${coord.latitud}, ${coord.longitud}", modifier = Modifier.weight(1f))
                    Button(onClick = {
                        CoordenadasFichadorALOT.coordenadas = mutableListOf(coord)
                    }) {
                        Text("Seleccionar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CambiarCoordenadasFichadorScreenPreview() {
    val navController = rememberNavController()
    CambiarCoordenadasFichadorScreen(navController = navController)
}
