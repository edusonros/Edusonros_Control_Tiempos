package com.example.composenavegacionentrepantallas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composenavegacionentrepantallas.screens.*

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("fichador_alot") {
            FichadorALOTScreen(navController)
        }
        composable("qr_trabajo") {
            QRTrabajoScreen(navController)
        }
        composable("qr_preparacion_maquina") {
            QRPreparacionMaquinaScreen(navController)
        }
        composable("qr_auxiliares") {
            QRAuxiliaresScreen(navController)
        }
        composable("qr_medico") {
            QRMedicoScreen(navController)
        }
        composable("configuracion") {
            ConfiguracionScreen(navController)
        }
        composable("editar_personal") {
            EditarPersonalScreen(navController)
        }
        composable("editar_operaciones_produccion") {
            EditarOperacionesProduccionScreen(navController)
        }
        composable("cambiar_coordenadas_fichador") {
            CambiarCoordenadasFichadorScreen(navController)
        }
        composable("cambiar_contraseña") {
            CambiarContrasenaScreen(navController)
        }
        composable("anadir-operario") {
            IngresarOperarioScreen(navController)
        }
        composable("anadir-operacion") {
            IngresarOperacionScreen(navController)
        }
    }
}

