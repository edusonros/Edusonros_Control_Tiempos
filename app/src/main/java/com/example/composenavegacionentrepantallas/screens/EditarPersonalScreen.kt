package com.example.composenavegacionentrepantallas.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun EditarPersonalScreen(navController: NavHostController) {
    var personal by remember { mutableStateOf(mutableMapOf(
        "ADRIAN BALLESTEROS" to 149,
        "ADRIANA RAD" to 320,
        "ALBERTO GARCIA PEREZ" to 117,
        "ALIEL HADDYOUY" to 324,
        "ANDRÉS SÁNCHEZ YUNTA" to 219,
        "ANTONIO PÉREZ BLANCO" to 230,
        "CARLOS CRESPO ORDOÑEZ" to 114,
        "CÉSAR BROTO" to 142,
        "DANIEL DEL HOYO" to 546,
        "DAVID BLASCO ACEVES" to 302,
        "EDUARDO SONSONA" to 540,
        "EMILIO VICENTE PAVÓN" to 226,
        "ENRIQUE MARÍN CERNUDA" to 543,
        "FLAVIUS RAD" to 105,
        "JAVIER FERRER LARRAYAD" to 429,
        "JAVIER ROS" to 127,
        "ALEXANDER JAMES" to 136,
        "JAVIER SALVADOR" to 218,
        "JAVIER SERAL" to 104,
        "JESÚS RUIZ MELENDO" to 503,
        "JOAQUIN CORTES" to 333,
        "JOSÉ ÁNGEL OCA" to 115,
        "JUÁN MORCILLO" to 334,
        "MIGUEL ÁNGEL ORTEGA" to 235,
        "MIGUEL ANGEL VA CHECA" to 111,
        "RAÚL CASTILLO" to 107,
        "RUBÉN GUALLAR" to 225,
        "RUBÉN LUÑO MOLINA" to 509,
        "SANTIAGO ALCAINE" to 216,
        "SEBASTIAN NICA" to 108,
        "VICTOR DIEGO DANTAS" to 328,
        "LUIS VICENTE" to 901,
        "RAFAEL SANCHEZ" to 908,
        "IGNACIO MARTÍNEZ DE LA VEGA" to 241,
        "RAFAEL MELO" to 242,
        "RUBÉN FEO" to 244,
        "IGNACIO CHAVES" to 245,
        "DAVID MARZO" to 324,
        "PEDRO JOSÉ PERNIAS" to 328,
        "JEAN SANTOS" to 332,
        "DIEGO DANTAS" to 331,
        "JAVIER FERRER" to 429,
        "VIRGINIA HERRANDO" to 544
    )) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Editar Personal", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        personal.forEach { (name, id) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BasicTextField(
                    value = name,
                    onValueChange = { newName -> personal.remove(name)?.let { personal[newName] = it } },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                BasicTextField(
                    value = id.toString(),
                    onValueChange = { newId -> personal[name] = newId.toIntOrNull() ?: id }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { personal.remove(name) }) {
                    Text("Eliminar")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Añadir lógica para agregar un nuevo operario
            personal["Nuevo Operario"] = 0
        }) {
            Text("Añadir Operario")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarPersonalScreenPreview() {
    EditarPersonalScreen(rememberNavController())
}